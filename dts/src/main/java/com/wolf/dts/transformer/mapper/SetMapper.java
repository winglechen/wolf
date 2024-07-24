package com.wolf.dts.transformer.mapper;

import lombok.NonNull;
import com.wolf.common.lang.io.db.Row;

/**
 * com.wolf.dts.transformation.mapper
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
