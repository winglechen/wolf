package com.onedot.win.framework.middleware.rdb.scanner;

import com.onedot.win.common.lang.enums.CodeBasedEnum;

/**
 * @author jings
 * @Date 2022/12/16
 */
public enum StepScanType implements CodeBasedEnum {
    SINGLE_TABLE(10),
    SINGLE_TABLE_MULTI_THREAD(11),
    SHARDING_TABLE(20),
    ;

    private int code;

    StepScanType(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getName() {
        return this.getName();
    }
}
