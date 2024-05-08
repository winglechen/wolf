package com.wolf.common.model.validator;

import com.wolf.common.model.contract.Rule;

/**
 * com.wolf.model.rule
 *
 * @author Wingle
 * @since 2019/10/15 12:25 下午
 **/
public class NullRule implements Rule {
    private boolean mustNull;
    private boolean notNull;
    private boolean allowNull;
}
