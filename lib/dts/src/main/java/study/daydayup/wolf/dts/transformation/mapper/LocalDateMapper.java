package study.daydayup.wolf.dts.transformation.mapper;

import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.util.time.DateUtil;

import java.util.Date;

/**
 * study.daydayup.wolf.dts.transformation.mapper
 *
 * @author Wingle
 * @since 2020/2/11 6:09 下午
 **/
public class LocalDateMapper extends AbstractMapper implements Mapper {
    @Override
    public void map(Row row) {
        Date value = (Date) row.get(column);
        if (value == null) {
            return;
        }

        String key = null != newColumn ? newColumn : column;
        row.put(key, DateUtil.asLocalDate(value));
    }
}
