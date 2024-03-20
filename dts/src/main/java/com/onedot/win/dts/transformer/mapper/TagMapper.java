package com.onedot.win.dts.transformer.mapper;

import com.onedot.win.common.io.db.Row;
import com.onedot.win.common.model.type.string.Tag;

/**
 * com.onedot.win.dts.transformation.mapper
 *
 * @author Wingle
 * @since 2020/2/11 6:09 下午
 **/
public class TagMapper extends AbstractMapper implements InitableMapper {
    @Override
    public void map(Row row) {
        Object value = row.get(column);
        if (value == null) {
            return;
        }

        row.put(column, new Tag((String) value));
    }
}
