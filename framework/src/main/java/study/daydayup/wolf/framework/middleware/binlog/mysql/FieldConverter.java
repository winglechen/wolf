package study.daydayup.wolf.framework.middleware.binlog.mysql;

import com.alibaba.dts.formats.avro.Field;
import org.apache.commons.lang3.StringUtils;


/**
 * FieldConverter interface
 *
 * @author: YIK
 * @since: 2022/3/1 10:18 AM
 */
public interface FieldConverter {
    static FieldConverter getConverter(String sourceName, String sourceVersion) {
        if (StringUtils.endsWithIgnoreCase("mysql", sourceName)) {
            return new MysqlFieldConverter();
        } else {
            throw new RuntimeException("FieldConverter: only mysql supported for now");
        }
    }

    FieldValue convert(Field field, Object t) throws Exception;
}
