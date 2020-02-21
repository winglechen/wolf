package study.daydayup.wolf.common.io.jdbc;

import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.collection.MapUtil;

import java.math.BigInteger;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * study.daydayup.wolf.common.io.jdbc
 *
 * @author Wingle
 * @since 2020/2/17 7:33 下午
 **/
public class JdbcMapper {

    public static List<Map<String, Object>> map(List<Map<String, Object>> list) {
        if (!CollectionUtil.notEmpty(list)) {
            return list;
        }

        ListIterator<Map<String, Object>> li = list.listIterator();
        while (li.hasNext()) {
            Map<String, Object> row = li.next();
            li.set(map(row));
        }

        return list;
    }

    public static Map<String, Object> map(Map<String, Object> row) {
        if (MapUtil.isEmpty(row)) {
            return row;
        }

        for (Map.Entry<String, Object> entry : row.entrySet()) {
            entry.setValue(convert(entry.getValue()));
        }

        return row;
    }

    public static Object convert(Object o) {
        if (o instanceof BigInteger) {
            return convert((BigInteger)o);
        }

        return o;
    }

    public static Long convert(BigInteger o) {
        return o.longValue();
    }


}
