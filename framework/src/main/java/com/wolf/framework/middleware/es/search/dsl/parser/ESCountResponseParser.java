package com.wolf.framework.middleware.es.search.dsl.parser;

import com.alibaba.fastjson.JSONObject;
import com.wolf.common.io.sql.SqlResult;

public class ESCountResponseParser {
    public static SqlResult toResult(JSONObject response) {
        Long count = response.getLong("count");

        SqlResult sqlResult = new SqlResult();
        sqlResult.setCount(count);
        sqlResult.setRealCountFlag(true);

        return sqlResult;
    }
}
