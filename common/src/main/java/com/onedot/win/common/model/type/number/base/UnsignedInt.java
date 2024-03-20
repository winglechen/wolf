package com.onedot.win.common.model.type.number.base;

import com.onedot.win.common.model.contract.DataType;

/**
 * com.onedot.win.model.type.number
 *
 * @author Wingle
 * @since 2019/10/16 9:43 上午
 **/
public class UnsignedInt implements DataType {
    private int length = 11;

    private boolean unsigned = false;
    private boolean positive = false;
    private boolean negative = true;
    private int defaultValue = 0;
}
