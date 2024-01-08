package study.daydayup.wolf.common.lang.contract.worker;

/**
 * study.daydayup.wolf.framework.layer.converter
 *
 * @author Wingle
 * @since 2020/1/11 2:01 下午
 **/
public interface Formatter<Input, Output> {
    Output format(Input input);
}
