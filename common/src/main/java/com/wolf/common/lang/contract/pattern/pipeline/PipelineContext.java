package com.wolf.common.lang.contract.pattern.pipeline;

import com.wolf.common.lang.contract.Context;

public interface PipelineContext extends Context {

    boolean isTerminate();

    void terminate();
}
