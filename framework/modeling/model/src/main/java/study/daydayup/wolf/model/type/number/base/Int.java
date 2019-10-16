package study.daydayup.wolf.model.type.number.base;

import study.daydayup.wolf.model.contract.DataType;

/**
 * study.daydayup.wolf.model.type.number
 *
 * @author Wingle
 * @since 2019/10/16 9:43 上午
 **/
public class Int implements DataType {
    private int length = 11;

    private boolean unsigned = false;
    private boolean positive = false;
    private boolean negative = true;
    private int defaultValue = 0;
}
