package study.daydayup.wolf.common.lang.ds;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Map;

/**
 * study.daydayup.wolf.common.lang.ds
 *
 * @author Wingle
 * @since 2020/2/29 5:20 下午
 **/
public class ObjectMap extends JSONObject implements Map<String, Object>,Serializable {
    public String toJson() {
        return JSON.toJSONString(getInnerMap());
    }

    public ObjectMap set(String key, Object value) {
        put(key, value);

        return this;
    }
    public Map<String, Object> getMap() {
        return getInnerMap();
    }
}
