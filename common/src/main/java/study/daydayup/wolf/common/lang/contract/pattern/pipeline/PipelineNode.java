package study.daydayup.wolf.common.lang.contract.pattern.pipeline;

public interface PipelineNode<T extends PipelineContext> {

    boolean shouldProceed(T context);

    void process(T context);
}
