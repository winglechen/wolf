package com.wolf.common.contract.pattern.pipeline;

public interface PipelineNode<T extends PipelineContext> {

    boolean shouldProceed(T context);

    void process(T context);
}
