package study.daydayup.wolf.common.lang.contract.pattern.pipeline;

import study.daydayup.wolf.common.lang.contract.pattern.factory.Factory;

public interface PipelineFactory<T extends PipelineContext> extends Factory {

    Pipeline<T> initPipeline();
}
