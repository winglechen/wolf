package study.daydayup.wolf.common.io.db;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2019/11/25 9:40 下午
 **/
public class Row extends HashMap<String, Object> implements Serializable {
    Row() {
        super();
    }

    Row(Map<String, Object> m) {
        super(m);
    }

    public static Row of(Map<String, Object> data) {

        return new Row(data);
    }
}
