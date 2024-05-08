package com.wolf.framework.middleware.es.search.sql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.wolf.common.io.sql.SqlExecutor;
import com.wolf.common.io.sql.SqlResult;
import com.wolf.common.io.sql.builder.SqlBuilder;
import com.wolf.common.util.lang.StringUtil;
import com.wolf.framework.middleware.es.config.ESConfig;
import com.wolf.framework.middleware.es.config.ServerConfig;
import com.wolf.framework.middleware.es.exception.InvalidESConfigException;
import com.wolf.framework.middleware.es.util.ResponseParser;
import com.wolf.framework.rpc.http.client.HttpClient;

@Slf4j
public class ESSqlExecutor implements SqlExecutor {
    private boolean debug = false;
    @Setter
    private ESConfig esConfig;

    @Override
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public SqlResult execute(SqlBuilder sqlBuilder, boolean debugInCurrentSession) {
        return execute(ESQuery.fromSqlBuilder(sqlBuilder), null, debugInCurrentSession);
    }

    @Override
    public SqlResult execute(SqlBuilder sqlBuilder) {
        return execute(sqlBuilder, this.debug);
    }

    public SqlResult execute(ESSqlBuilder sql) {
        return execute(sql, this.debug);
    }

    public SqlResult execute(ESSqlBuilder sql, boolean debugInCurrentSession) {
        ESOption option = ESOption.builder()
                .format(sql.getFormat())
                .server(sql.getServer())
                .build();

        return execute(ESQuery.fromESSqlBuilder(sql), option, debugInCurrentSession);
    }

    @Override
    public SqlResult execute(String sql) {
        return execute(ESQuery.fromSql(sql));
    }

    public SqlResult execute(ESQuery query) {
        return execute(query, null, this.debug);
    }

    private SqlResult execute(ESQuery query, ESOption option, boolean debugInCurrentSession) {
        if (debugInCurrentSession) {
            log.info("query: {}, option: {};", JSON.toJSONString(query), JSON.toJSONString(option));
        }

        JSONObject response = call(query, option);
        if (debugInCurrentSession) {
            log.info("es query response: {}", JSON.toJSONString(response));
        }

        SqlResult result = new SqlResult();
        result.setCursor(response.getString("cursor"));
        result.setData(ResponseParser.toMapList(response));

        return result;
    }

    private JSONObject call(ESQuery query, ESOption option) {
        if (esConfig == null) {
            throw new InvalidESConfigException();
        }

        if (option == null) {
            option = new ESOption();
        }

        ServerConfig config = esConfig.getServer().get(option.getServer());
        if (config == null) {
            throw new InvalidESConfigException("server " + option + " config not found");
        }

        String url = StringUtil.join(
                config.getProtocol(), "://", config.getHostname()
                , ":", config.getPort()
                , "/_sql?format=", option.getFormat()
        );

        return HttpClient.builder()
                .url(url)
                .basicAuth(config.getUsername(), config.getPassword())
                .post(query)
                .execute()
                .getJson();
    }

}
