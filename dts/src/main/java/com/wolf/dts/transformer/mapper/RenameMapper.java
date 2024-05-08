package com.wolf.dts.transformer.mapper;

import lombok.NonNull;
import com.wolf.common.io.db.Row;

/**
 * com.wolf.dts.transformation.mapper
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
