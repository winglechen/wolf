package com.wolf.dts.transformer.mapper;

import com.wolf.common.io.db.Row;
import com.wolf.common.lang.ds.Tag;

/**
 * com.wolf.dts.transformation.mapper
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
