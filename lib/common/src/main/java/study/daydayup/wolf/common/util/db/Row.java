package study.daydayup.wolf.common.util.db;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.common.util.db
 *
 * @author Wingle
 * @since 2019/11/25 9:40 下午
 **/
public class Row extends HashMap<String, String> implements Serializable {
    Row() {
        super();
    }

    Row(Map<String, String> m) {
        super(m);
    }

    public static Row of(Map<String, Object> data) {

        Map<String, String> m = new HashMap<>();
        for(Map.Entry<String, Object> entry : data.entrySet()) {
            m.put(entry.getKey(), entry.getValue().toString());
        }

        return new Row(m);
    }
}
