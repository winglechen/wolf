package com.wolf.dts.transformer.mapper;

import com.wolf.common.io.db.Row;
import com.wolf.common.util.time.DateUtil;

import java.sql.Timestamp;
import java.util.Date;

public class MinuteMapper extends AbstractMapper implements InitableMapper {
    @Override
    public void map(Row row) {
        String key = null != newColumn ? newColumn : column;
        Object value = row.get(column);
        if (value == null) {
            return;
        }

        if (value instanceof Timestamp) {
            row.put(key, ((Timestamp) value).toLocalDateTime().getMinute());
            return;
        }

        row.put(key, DateUtil.asLocalDateTime((Date) value).getMinute());
    }
}