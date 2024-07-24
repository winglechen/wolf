package com.wolf.common.contract.pattern.pipeline;

import com.wolf.common.contract.container.Context;

public interface PipelineContext extends Context {

    boolean isTerminate();

    void terminate();
}
