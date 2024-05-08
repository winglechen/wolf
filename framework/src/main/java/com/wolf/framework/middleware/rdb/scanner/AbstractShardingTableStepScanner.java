package com.wolf.framework.middleware.rdb.scanner;

import lombok.extern.slf4j.Slf4j;
import com.wolf.common.util.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jings
 * @Date 2022/12/18
 */
@Slf4j
public abstract class AbstractShardingTableStepScanner extends AbstractTableStepScanner {

    @Override
    public boolean supportScanType(StepScanType scanType) {
        return StepScanType.SHARDING_TABLE == scanType;
    }

    @Override
    public List<StepTaskParams> genStepTaskParams(String scanId, TableStepScanInitParams initParams) {

        List<DBTableShardPair> shardList = null;
        Integer dbShard = initParams.getDbShard();
        Integer tableShard = initParams.getTableShard();
        if (dbShard == null
                && tableShard == null) {
            shardList = getDBTableShardList();
        } else {
            shardList = new ArrayList<>(1);
            shardList.add(DBTableShardPair.create(dbShard, tableShard));
        }

        if (CollectionUtil.isEmpty(shardList)) {
            return null;
        }

        // calculate table shards
        List<StepTaskParams> stepOneTasks = new LinkedList<>();
        for (DBTableShardPair shardPair : shardList) {
            Offset offset = calcOffset4EveryShard(shardPair, initParams);
            if (offset == null) {
                continue;
            }

            StepTaskParams stepTaskParams = StepTaskParams.builder()
                    .table(getTableName())
                    .scanType(initParams.getScanType())
                    .firstOffset(offset.getFirstOffset())
                    .endOffset(offset.getEndOffset())
                    .curOffset(offset.getFirstOffset())
                    .scanId(scanId)
                    .dbShard(shardPair.getDbShard())
                    .tableShard(shardPair.getTableShard())
                    .stepBatchSize(initParams.getStepBatchSize())
                    .initParams(initParams)
                    .build();

            stepOneTasks.add(stepTaskParams);
        }

        return stepOneTasks;
    }


    abstract public List<DBTableShardPair> getDBTableShardList();

    abstract public Offset calcOffset4EveryShard(DBTableShardPair shardPair, TableStepScanInitParams initParams);

}
