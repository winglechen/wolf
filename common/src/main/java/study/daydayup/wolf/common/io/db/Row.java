package study.daydayup.wolf.common.io.db;

import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.common.util.time.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2019/11/25 9:40 下午
 **/
public class Row extends ObjectMap implements Serializable {
    public Row() {
        super(new HashMap<>());

    }

    public Row(Map<String, Object> m) {
        super(m);
    }

    public static Row of(Map<String, Object> data) {

        return new Row(data);
    }

    public Long getId() {
        return getLong(Table.DEFAULT_ID_COLUMN);
    }


    /**
     * 2007-12-03T10:15:30
     * 2007-12-03 10:15:30
     * 2007-12-03 10:15:30.001
     *
     * @param key
     * @return
     */
    public LocalDateTime getLocalDateTime(String key) {
        String s = this.getString(key);
        return DateUtil.asLocalDateTimeCompatibleMode(s);
    }

    public LocalDate getLocalDate(String key) {
        String s = this.getString(key);
        return DateUtil.asLocalDate(s);
    }

}
