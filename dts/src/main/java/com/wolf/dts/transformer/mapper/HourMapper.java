package com.wolf.dts.transformer.mapper;

import com.wolf.common.lang.io.db.Row;
import com.wolf.common.util.time.DateUtil;

import java.sql.Timestamp;
import java.util.Date;

public class HourMapper extends AbstractMapper implements InitableMapper {
    @Override
    public void map(Row row) {
        String key = null != newColumn ? newColumn : column;
        Object value = row.get(column);
        if (value == null) {
            return;
        }

        if (value instanceof Timestamp) {
            row.put(key, ((Timestamp) value).toLocalDateTime().getHour());
            return;
        }

        row.put(key, DateUtil.asLocalDateTime((Date) value).getHour());
    }
}
