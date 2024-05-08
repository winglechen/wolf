package com.wolf.framework.middleware.es.search.dsl.executor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.wolf.common.io.sql.SqlResult;
import com.wolf.common.lang.ds.ObjectMap;
import com.wolf.common.util.lang.StringUtil;
import com.wolf.framework.middleware.es.config.ESConfig;
import com.wolf.framework.middleware.es.config.ServerConfig;
import com.wolf.framework.middleware.es.exception.InvalidESConfigException;
import com.wolf.framework.middleware.es.search.dsl.ESCountBuilder;
import com.wolf.framework.middleware.es.search.dsl.ESSearchBuilder;
import com.wolf.framework.middleware.es.search.dsl.parser.ESSearchResponseParser;
import com.wolf.framework.rpc.http.ContentTypeEnum;
import com.wolf.framework.rpc.http.client.HttpClient;

@Slf4j
public class ESSearchExecutor {
    @Setter
    private ESConfig esConfig;

    @Setter
    private boolean debug = false;

    public SqlResult execute(ESSearchBuilder esSearchBuilder) {
        return execute(esSearchBuilder, this.debug);
    }

    public SqlResult execute(ESSearchBuilder esSearchBuilder, boolean debugInCurrentSession) {
        ObjectMap dsl = esSearchBuilder.toDsl();
        String jsonString = dsl.toJSONString();
        String indexName = esSearchBuilder.getIndexName();

        if (debugInCurrentSession) {
            log.info("es query index: {} request: {}", indexName, jsonString);
        }

        JSONObject response = call(indexName, jsonString);
        if (debugInCurrentSession) {
            log.info("es query index: {}; response: {}", indexName, JSON.toJSONString(response));
        }

        SqlResult sqlResult = ESSearchResponseParser.toResult(response);
        if (esSearchBuilder.getWithRealCount() && !sqlResult.isRealCountFlag()) {
            fetchRealCount(esSearchBuilder, sqlResult);
        }

        return sqlResult;
    }

    private JSONObject call(String indexName, String jsonString) {
        if (esConfig == null) {
            throw new InvalidESConfigException();
        }

        ServerConfig config = esConfig.getServer().get("default");
        if (config == null) {
            throw new InvalidESConfigException("server default config not found");
        }

        String url = StringUtil.join(
                config.getProtocol(), "://", config.getHostname()
                , ":", config.getPort()
                , "/", indexName, "/_search"
        );

        return HttpClient.builder()
                .url(url)
                .basicAuth(config.getUsername(), config.getPassword())
                .post(jsonString, ContentTypeEnum.JSON)
                .execute()
                .getJson();
    }

    private <T> void fetchRealCount(ESSearchBuilder esSearchBuilder, SqlResult sqlResult) {
        ESCountExecutor esCountExecutor = new ESCountExecutor();
        esCountExecutor.setEsConfig(this.esConfig);

        ESCountBuilder esCountBuilder = new ESCountBuilder();
        esCountBuilder.setExecutor(esCountExecutor);

        esCountBuilder.setEsWhereBuilder(esSearchBuilder.getEsWhereBuilder());

        SqlResult countResult = esCountBuilder
                .from(esSearchBuilder.getIndexName())
                .execute(this.debug);

        sqlResult.setCount(countResult.getCount());
        sqlResult.setRealCountFlag(countResult.isRealCountFlag());
    }
}
