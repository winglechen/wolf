package study.daydayup.wolf.framework.middleware.es.search.dsl.test;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import study.daydayup.wolf.common.io.enums.OrderEnum;
import study.daydayup.wolf.common.io.sql.SqlResult;
import study.daydayup.wolf.framework.middleware.es.config.ESConfig;
import study.daydayup.wolf.framework.middleware.es.config.ServerConfig;
import study.daydayup.wolf.framework.middleware.es.search.dsl.ESSearchBuilder;
import study.daydayup.wolf.framework.middleware.es.search.dsl.executor.ESSearchExecutor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
public class ESSearchBuilderTest {
    public static void main(String[] args) {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setProtocol("http");
        serverConfig.setHostname("localhost");
        serverConfig.setPort("9200");
        serverConfig.setUsername("elastic");
        serverConfig.setPassword("12345678");

        ESConfig esConfig = new ESConfig();
        esConfig.getServer().put("default", serverConfig);

        ESSearchExecutor esSearchExecutor = new ESSearchExecutor();
        esSearchExecutor.setEsConfig(esConfig);

        ESSearchBuilder builder = new ESSearchBuilder();
        builder.setExecutor(esSearchExecutor);

        SqlResult result = builder.from("payment_attempt")
                .select("id, payment_attempt_no, amount")
                .where("payee_id", "=", 100000005)
                .where("state", "=", 4)
                .where("created_at", ">=", LocalDateTime.of(LocalDate.of(2022, 4, 7), LocalTime.MIN))
                .where("created_at", "<", LocalDateTime.of(LocalDate.of(2022, 4, 7), LocalTime.MAX))
                .where("paid_at", "<", LocalDateTime.of(LocalDate.of(2022, 4, 7), LocalTime.MAX))
                .limit(1000L, 4)
                .orderBy("created_at", OrderEnum.DESC)
                .orderBy("paid_at", OrderEnum.DESC)
                .withRealCount()
                .execute(true);

        log.info("count={} isRealCount={} data={}", result.getCount(), result.isRealCountFlag(), result.getObjectList(SomeDO.class));
    }
}


@Data
class SomeDO implements Serializable {
    private Long id;
    private String paymentNo;
    private Long payerId;
    private String payerName;
    private Long payeeId;
    private String payeeName;
    private BigDecimal amount;
    private Integer currency;
    private Integer state;
    private Integer tradeSource;
    private Integer paymentChannel;
    private String tradeNo;
    private String outTradeNo;
    private Long goodsId;
    private String goodsName;
    private String goodsDescription;
    private String tags;
    private String attachment;
    private String env;
    private Integer version;
    private Boolean deleteFlag;
    private Long lastEditor;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime paidAt;
    private LocalDateTime outPaidAt;
    private String payerPhone;
    private String payerEmail;
    private String paymentMode;
    private BigDecimal refundAmount;
    private String utr;
}