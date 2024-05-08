package com.wolf.framework.middleware.es.search.dsl.executor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.wolf.common.io.sql.SqlResult;
import com.wolf.common.util.lang.StringUtil;
import com.wolf.framework.middleware.es.config.ESConfig;
import com.wolf.framework.middleware.es.config.ServerConfig;
import com.wolf.framework.middleware.es.exception.InvalidESConfigException;
import com.wolf.framework.middleware.es.search.dsl.ESCountBuilder;
import com.wolf.framework.middleware.es.search.dsl.parser.ESCountResponseParser;
import com.wolf.framework.rpc.http.ContentTypeEnum;
import com.wolf.framework.rpc.http.client.HttpClient;

@Slf4j
public class ESCountExecutor {
    @Setter
    private ESConfig esConfig;

    @Setter
    private boolean debug = false;

    public SqlResult execute(ESCountBuilder esSearchBuilder) {
        return execute(esSearchBuilder, this.debug);
    }

    public SqlResult execute(ESCountBuilder esCountBuilder, boolean debugInCurrentSession) {
        JSONObject dsl = esCountBuilder.toDsl();
        String jsonString = dsl.toJSONString();
        String indexName = esCountBuilder.getIndexName();

        if (debugInCurrentSession) {
            log.info("{}/_count {}", indexName, jsonString);
        }

        JSONObject response = call(indexName, jsonString);

        if (debugInCurrentSession) {
            log.info("es query response: {}", JSON.toJSONString(response));
        }

        return ESCountResponseParser.toResult(response);
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
                , "/", indexName, "/_count"
        );

        return HttpClient.builder()
                .url(url)
                .basicAuth(config.getUsername(), config.getPassword())
                .post(jsonString, ContentTypeEnum.JSON)
                .execute()
                .getJson();
    }
}