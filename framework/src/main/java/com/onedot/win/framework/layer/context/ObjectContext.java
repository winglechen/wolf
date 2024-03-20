package com.onedot.win.framework.layer.context;

import com.alibaba.fastjson.JSON;
import lombok.NonNull;
import com.onedot.win.common.lang.contract.Context;

import java.util.Map;

/**
 * com.onedot.win.framework.layer.context
 *
 * @author Wingle
 * @since 2020/2/29 3:33 下午
 **/
public class ObjectContext implements Context {
    protected Map<String, Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public Object get(@NonNull String key) {
        return data.get(key);
    }

    public ObjectContext set(@NonNull String key, Object value) {
        data.put(key, value);
        return this;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

}
