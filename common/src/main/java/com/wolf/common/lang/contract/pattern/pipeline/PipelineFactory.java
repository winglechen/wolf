package com.wolf.common.lang.contract.pattern.pipeline;

import com.wolf.common.lang.contract.pattern.factory.Factory;

public interface PipelineFactory<T extends PipelineContext> extends Factory {

    Pipeline<T> initPipeline();
}
