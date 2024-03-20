package com.onedot.win.common.model.validator;

import com.onedot.win.common.model.contract.Rule;

/**
 * com.onedot.win.model.rule
 *
 * @author Wingle
 * @since 2019/10/15 12:25 下午
 **/
public class NullRule implements Rule {
    private boolean mustNull;
    private boolean notNull;
    private boolean allowNull;
}
