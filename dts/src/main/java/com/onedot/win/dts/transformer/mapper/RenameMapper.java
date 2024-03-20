package com.onedot.win.dts.transformer.mapper;

import lombok.NonNull;
import com.onedot.win.common.io.db.Row;

/**
 * com.onedot.win.dts.transformation.mapper
 *
 * @author Wingle
 * @since 2020/2/11 6:09 下午
 **/
public class RenameMapper extends AbstractMapper implements InitableMapper {
    @Override
    public void map(@NonNull Row row) {
        Object value = row.get(column);
        if (value == null) {
            return;
        }

        row.remove(column);
        row.put(newColumn, value);
    }
}
