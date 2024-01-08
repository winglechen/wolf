package study.daydayup.wolf.framework.middleware.rdb.scanner;

/**
 * @author jings
 * @Date 2022/9/30
 */
public interface TableStepScanner {

    boolean supportScanType(StepScanType scanType);

    String start(TableStepScanInitParams initParams);

    void scanAndProcessStep(StepTaskParams stepParams);
}
