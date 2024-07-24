package com.wolf.common.contract.pattern.pipeline;

import com.wolf.common.contract.pattern.factory.Factory;

public interface PipelineFactory<T extends PipelineContext> extends Factory {

    Pipeline<T> initPipeline();
}
