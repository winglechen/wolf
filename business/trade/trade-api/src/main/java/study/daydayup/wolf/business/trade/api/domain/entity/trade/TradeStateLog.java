package study.daydayup.wolf.business.trade.api.domain.entity.trade;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.framework.layer.api.Model;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.trade.api.domain.entity.trade
 *
 * @author Wingle
 * @since 2019/12/26 7:24 下午
 **/
@Data
@SuperBuilder
@NoArgsConstructor
public class TradeStateLog implements Model {
    private Integer id;

    private String tradeNo;
    private String relatedTradeNo;
    private Integer tradeType;

    private Long buyerId;
    private Long sellerId;

    private Integer sourceState;
    private Integer targetState;

    private Long amount;
    private Integer paymentMethod;
    private Integer consignMethod;
    private String tags;
    private String source;

    private Integer sourceVersion;
    private Integer targetVersion;
    private LocalDateTime createdAt;

}
