package com.onedot.win.dts.config;

import lombok.Data;

/**
 * com.onedot.win.business.union.task.config
 *
 * @author Wingle
 * @since 2020/2/10 10:14 上午
 **/
@Data
public class OffsetConfig {
    public static final String TABLE_NAME = "offset_holder";

    public String getTable() {
        return TABLE_NAME;
    }
}
