package study.daydayup.wolf.framework.middleware.es.search.dsl.test;

import lombok.extern.slf4j.Slf4j;
import study.daydayup.wolf.common.io.sql.SqlResult;
import study.daydayup.wolf.framework.middleware.es.config.ESConfig;
import study.daydayup.wolf.framework.middleware.es.config.ServerConfig;
import study.daydayup.wolf.framework.middleware.es.search.dsl.ESCountBuilder;
import study.daydayup.wolf.framework.middleware.es.search.dsl.executor.ESCountExecutor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

@Slf4j
public class ESCountBuilderTest {
    public static void main(String[] args) {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setProtocol("http");
        serverConfig.setHostname("localhost");
        serverConfig.setPort("9200");
        serverConfig.setUsername("elastic");
        serverConfig.setPassword("12345678");

        ESConfig esConfig = new ESConfig();
        esConfig.getServer().put("default", serverConfig);

        ESCountExecutor esCountExecutor = new ESCountExecutor();
        esCountExecutor.setEsConfig(esConfig);

        ESCountBuilder builder = new ESCountBuilder();
        builder.setExecutor(esCountExecutor);

        SqlResult result = builder.from("payment_attempt")
                .where("payee_id", "=", 100000005)
                .where("state", "in", Arrays.asList(1, 2))
                .where("created_at", ">=", LocalDateTime.of(LocalDate.of(2022, 4, 7), LocalTime.MIN))
                .where("created_at", "<", LocalDateTime.of(LocalDate.of(2022, 4, 7), LocalTime.MAX))
                .execute(true);

        log.info("{} {}", result.getCount(), result.isRealCountFlag());
    }
}
