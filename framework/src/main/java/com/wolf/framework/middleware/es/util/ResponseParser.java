package com.wolf.framework.middleware.es.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseParser {
    public static List<Map<String, Object>> toMapList(JSONObject response) {
        JSONArray columns = response.getJSONArray("columns");
        JSONArray rows = response.getJSONArray("rows");
        List<Map<String, Object>> result = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {
            JSONArray row = rows.getJSONArray(i);
            Map<String, Object> data = new HashMap<>(row.size());

            for (int j = 0; j < row.size(); j++) {
                String key = columns.getJSONObject(j).getString("name");
                data.put(key, row.get(j));
            }

            result.add(data);
        }

        return result;
    }
}
