package study.daydayup.wolf.business.trade.api.dto.buy.base;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy.base
 *
 * @author Wingle
 * @since 2020/3/9 11:20 上午
 **/
@Data
public class TradeNotification implements Request {
    private String tradeNo;
    private String paymentNo;
    private String outTradeNo;

    private Long payerId;
    private Long payeeId;


    private BigDecimal amount;
    private Integer currency;
    private Integer state;
    private Integer paymentMethod;

    private String tags;

    private LocalDateTime paidAt;
}
