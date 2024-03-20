package com.onedot.win.dts.transformer.mapper;

import com.onedot.win.common.io.db.Row;
import com.onedot.win.common.util.time.DateUtil;

import java.sql.Timestamp;
import java.util.Date;

/**
 * com.onedot.win.dts.transformation.mapper
 *
 * @author Wingle
 * @since 2020/2/11 6:09 下午
 **/
public class LocalDateMapper extends AbstractMapper implements InitableMapper {
    @Override
    public void map(Row row) {
        String key = null != newColumn ? newColumn : column;
        Object value = row.get(column);
        if (value == null) {
            return;
        }

        if (value instanceof Timestamp) {
            row.put(key, ((Timestamp) value).toLocalDateTime().toLocalDate());
            return;
        }

        row.put(key, DateUtil.asLocalDate((Date) value));
    }
}
