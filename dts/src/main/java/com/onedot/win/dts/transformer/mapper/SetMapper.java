package com.onedot.win.dts.transformer.mapper;

import lombok.NonNull;
import com.onedot.win.common.io.db.Row;

/**
 * com.onedot.win.dts.transformation.mapper
 *
 * @author Wingle
 * @since 2020/2/11 6:09 下午
 **/
public class SetMapper implements Mapper {
    protected String column;
    protected Object value;

    public void init(@NonNull String column, Object value) {
        this.column = column;
        this.value = value;
    }

    @Override
    public void map(@NonNull Row row) {
        row.put(column, value);
    }

}
