package com.onedot.win.framework.middleware.es.search.dsl.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.onedot.win.common.io.sql.SqlResult;
import com.onedot.win.common.util.collection.ListUtil;
import com.onedot.win.framework.middleware.es.enums.ESOperatorEnum;

public class ESSearchResponseParser {
    public static SqlResult toResult(JSONObject response) {
        JSONObject hits = response.getJSONObject("hits");
        JSONObject total = hits.getJSONObject("total");

        SqlResult sqlResult = new SqlResult();
        sqlResult.setCount(total.getLong("value"));
        sqlResult.setRealCountFlag(ESOperatorEnum.EQUAL.getName().equalsIgnoreCase(total.getString("relation")));

        JSONArray hitsJSONArray = hits.getJSONArray("hits");
        if (ListUtil.isEmpty(hitsJSONArray)) {
            return sqlResult;
        }

        for (Object hitObject : hitsJSONArray) {
            JSONObject hitJSONObject = (JSONObject) hitObject;
            sqlResult.addData(hitJSONObject.getJSONObject("_source").getInnerMap());
        }

        return sqlResult;
    }
}
