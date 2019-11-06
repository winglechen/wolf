package study.daydayup.wolf.model.validator;

import study.daydayup.wolf.model.contract.Rule;

/**
 * study.daydayup.wolf.model.rule
 *
 * @author Wingle
 * @since 2019/10/15 12:25 下午
 **/
public class NullRule implements Rule {
    private boolean mustNull;
    private boolean notNull;
    private boolean allowNull;
}
