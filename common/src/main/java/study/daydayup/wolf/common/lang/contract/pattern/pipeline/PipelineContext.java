package study.daydayup.wolf.common.lang.contract.pattern.pipeline;

import study.daydayup.wolf.common.lang.contract.Context;

public interface PipelineContext extends Context {

    boolean isTerminate();

    void terminate();
}
