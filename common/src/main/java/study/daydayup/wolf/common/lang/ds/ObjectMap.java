package study.daydayup.wolf.common.lang.ds;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import study.daydayup.wolf.common.lang.contract.Context;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.lang.JSONUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * study.daydayup.wolf.common.lang.ds
 *
 * @author Wingle
 * @since 2020/2/29 5:20 下午
 **/
public class ObjectMap extends JSONObject implements Map<String, Object>, Context, Serializable {
    public static ObjectMap newInstance() {
        return new ObjectMap();
    }

    public ObjectMap() {
    }

    public ObjectMap(Map<String, Object> map) {
        super(map);
    }

    public String toJson() {
        return JSON.toJSONString(getInnerMap());
    }

    public Map<String, Object> toMap() {
        return getInnerMap();
    }

    public ObjectMap kv(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public <T> ObjectMap addToCollection(String key, T value) {
        CollectionUtil.add(get(key), value);
        return this;
    }
    
//    @SuppressWarnings("unchecked")
//    public <T> ObjectMap addToCollection(String key, T value) {
//        Object target = get(key);
//
//        // if target is null init target
//        if (target == null) {
//            put(key, Collections.singletonList(value));
//            return this;
//        }
//
//        // if target is not collection quit
//        if (!(target instanceof Collection)) {
//            throw new IllegalArgumentException("can't add item to target whose type is not Collection");
//        }
//
//        Collection<Object> collection = (Collection<Object>) target;
//        //if collection is empty override with new collection
//        if (CollectionUtil.isEmpty(collection)) {
//            put(key, Collections.singletonList(value));
//            return this;
//        }
//
//        //if the type of first item of the collection not match the type of value quit
//        Object firstItem = CollectionUtil.first(collection);
//        assert firstItem != null;
//        if (!firstItem.getClass().getTypeName().equalsIgnoreCase(value.getClass().getTypeName())) {
//            throw new IllegalArgumentException("value type doesn't match target element type");
//        }
//
//        //if the type of first item of the collection match the type of value, add the value to the collection
//        ((Collection<T>) target).add(value);
//        return this;
//    }

    public JSONObject getJSONObject(String... keys) {
        return JSONUtil.getJSONObject(this, keys);
    }

    public <T> T getObject(String path, String key, Class<T> clazz) {
        JSONObject obj = JSONUtil.getJSONObject(this, path);
        if (obj == null) {
            return null;
        }

        return obj.getObject(key, clazz);
    }

    public Object get(String path, String key) {
        JSONObject obj = JSONUtil.getJSONObject(this, path);
        if (obj == null) {
            return null;
        }

        return obj.get(key);
    }

    public Integer getInteger(String path, String key) {
        JSONObject obj = JSONUtil.getJSONObject(this, path);
        if (obj == null) {
            return null;
        }

        return obj.getInteger(key);
    }

    public Boolean getBoolean(String path, String key) {
        JSONObject obj = JSONUtil.getJSONObject(this, path);
        if (obj == null) {
            return null;
        }

        return obj.getBoolean(key);
    }

    public Long getLong(String path, String key) {
        JSONObject obj = JSONUtil.getJSONObject(this, path);
        if (obj == null) {
            return null;
        }

        return obj.getLong(key);
    }

    public BigDecimal getBigDecimal(String path, String key) {
        JSONObject obj = JSONUtil.getJSONObject(this, path);
        if (obj == null) {
            return null;
        }

        return obj.getBigDecimal(key);
    }

    public String getString(String path, String key) {
        JSONObject obj = JSONUtil.getJSONObject(this, path);
        if (obj == null) {
            return null;
        }

        return obj.getString(key);
    }


}
