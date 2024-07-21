package com.wolf.framework.middleware.rdb.scanner;

import com.wolf.common.io.db.Row;
import com.wolf.common.util.collection.CollectionUtil;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jings
 * @Date 2022/12/16
 */
@Slf4j
public abstract class AbstractTableStepScanner extends AbstractCommonTableStepScanner implements TableStepScanner {
    //================================================ configs:start ================================================

    abstract public String getTableName();

    protected boolean ignoreInitFail() {
        return false;
    }


    //================================================ configs:end ================================================

    //================================================ init scan:start ================================================

    @Override
    public String start(TableStepScanInitParams initParams) {
        String scanId = genScanId();

        if (!this.supportScanType(initParams.getScanType())) {
            throw new UnsupportedOperationException();
        }

        List<StepTaskParams> stepOneTasks = genStepTaskParams(scanId, initParams);

        // save the first step tasks for every shard.
        int successCnt = 0;
        int failedCnt = 0;
        for (StepTaskParams stepOneTask : stepOneTasks) {
            try {
                boolean success = saveStepTask(stepOneTask);
                if (success) {
                    successCnt++;
                } else {
                    failedCnt++;
                    if (!ignoreInitFail()) {
                        log.error("[step_scanner] init tasks failed and it will stop all the sharding tasks from this scan." +
                                "scanId:{}, stepParams:{}", scanId, stepOneTask);
                        stopScan(scanId);
                        break;
                    }
                }
            } catch (Exception e) {
                if (!ignoreInitFail()) {
                    log.error("[step_scanner] init tasks failed and it will stop all the sharding tasks from this scan." +
                            "scanId:{}, stepParams:{}", scanId, stepOneTask, e);
                    stopScan(scanId);
                    break;
                } else {
                    log.error("[step_scanner] init tasks failed , but it will go on other tasks from this scan" +
                            "scanId:{}, stepParams:{}", scanId, stepOneTask, e);
                }
            }
        }

        log.info("[step_scanner] scan init finished. scanId:{}, successCnt:{}, failedCnt:{}, scanParams:{}, ignoreInitFail:{}",
                scanId, successCnt, failedCnt, initParams, ignoreInitFail());

        return isScannable(scanId) ? scanId : null;
    }

    abstract public List<StepTaskParams> genStepTaskParams(String scanId, TableStepScanInitParams tableStepScanParams);

    abstract public boolean saveStepTask(StepTaskParams stepParams);
    //================================================ init scan:end ================================================


    //================================================ process step task:start ================================================
    public void scanAndProcessStep(StepTaskParams stepParams) {
        try {
            if (!isScannable(stepParams.getScanId())) {
                log.info("[step_scanner].process scannable = false. stepParams:{}", stepParams);
                return;
            }

            // read data
            List<Row> rows = scanRowsByStep(stepParams);
            if (CollectionUtil.isEmpty(rows)) {
                log.info("[step_scanner].process finished one of the shard tasks. stepParams:{}", stepParams);
                return;
            }
            // calculate params for the next step
            boolean hasNext = isHasNext(stepParams, rows);
            Long nextOffset = rows.get(rows.size() - 1).getId();

            // filter data
            rows = filterRowsInStep(stepParams, rows);

            if (CollectionUtil.notEmpty(rows)) {
                // do process
                boolean processSuccess = processStep(stepParams, rows);

                // after process
                if (!processSuccess) {
                    if (resaveStepTaskOnFiled() && !isOverMaxRetryTimes(stepParams)) {
                        stepParams.incrRetryCnt();
                        saveStepTask(stepParams);
                        log.warn("[step_scanner].process step process failed. then resave the same task again. stepParams:{}", stepParams);
                    } else {
                        log.error("[step_scanner].process step process failed. won't retry any more. stepParams:{}", stepParams);
                    }
                    return;
                }
            }

            if (hasNext) {
                stepParams.setCurOffset(nextOffset);
                stepParams.resetRetryCnt();
                boolean success = saveStepTask(stepParams);
                if (success) {
                    log.info("[step_scanner].process send next task success. scanId:{}, params:{}", stepParams.getScanId(), stepParams);
                } else {
                    log.error("[step_scanner].process send next task failed. scanId:{}, params:{}", stepParams.getScanId(), stepParams);
                }
            } else {
                log.info("[step_scanner].process finished one of the shard tasks. stepParams:{}", stepParams);
            }
        } catch (Exception e) {
            log.error("[step_scanner].consumer failed,then resend to mq, stepParams:{}", stepParams, e);

            resaveStepTaskOnFailed(stepParams);
        }

    }

    private void resaveStepTaskOnFailed(StepTaskParams stepParams) {
        if (resaveStepTaskOnFiled() && !isOverMaxRetryTimes(stepParams)) {
            stepParams.incrRetryCnt();
            boolean success = saveStepTask(stepParams);
            if (success) {
                log.warn("[step_scanner].process step process failed. then resave the same task again. stepParams:{}", stepParams);
                return;
            }
        }

        log.error("[step_scanner].process step process failed. won't retry any more. stepParams:{}", stepParams);
    }

    protected boolean isHasNext(StepTaskParams stepParams, List<Row> rows) {
        return stepParams.getStepBatchSize() == rows.size();
    }

    /**
     * scan data from db for one of the step in a table shard.
     * you should return the origin data from db without do any filter, because it will calculate the next step depend on rows.size().
     * if you want to filter the data before processing , you could overwrite the method:
     *
     * @see AbstractTableStepScanner#filterRowsInStep(StepTaskParams, List)
     */
    abstract public List<Row> scanRowsByStep(StepTaskParams stepParams);

    protected List<Row> filterRowsInStep(StepTaskParams stepParams, List<Row> rows) {
        return rows;
    }

    protected abstract boolean processStep(StepTaskParams stepParams, List<Row> rows);


    //================================================ process step task:end ================================================

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    protected static class Offset {
        private Long firstOffset;
        private Long endOffset;

    }

}
