package com.onedot.win.common.lang.contract.pattern.pipeline;

import com.onedot.win.common.lang.contract.Context;

public interface PipelineContext extends Context {

    boolean isTerminate();

    void terminate();
}
