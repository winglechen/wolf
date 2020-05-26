package study.daydayup.wolf.bigdata.datav.api.entity.daily;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DailyTrade implements Model {
    private Long id;

    private Long orgId;
    private LocalDate date;

    private Integer tradeType;
    private Integer tradeState;
    private String source;

    private Integer tradeCount;
    private Integer buyerCount;
    private BigDecimal tradeAmount;

    private Boolean deleteFlag;
    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;
}