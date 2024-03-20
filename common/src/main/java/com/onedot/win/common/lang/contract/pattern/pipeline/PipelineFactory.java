package com.onedot.win.common.lang.contract.pattern.pipeline;

import com.onedot.win.common.lang.contract.pattern.factory.Factory;

public interface PipelineFactory<T extends PipelineContext> extends Factory {

    Pipeline<T> initPipeline();
}
