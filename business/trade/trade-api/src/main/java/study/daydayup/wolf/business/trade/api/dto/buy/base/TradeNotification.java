package study.daydayup.wolf.business.trade.api.dto.buy.base;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank
    private String tradeNo;
    @NotBlank
    private String paymentNo;
    private String outTradeNo;

    @NotBlank
    private Long payerId;
    @NotBlank
    private Long payeeId;


    @NotNull @Min(1)
    private Integer paymentMethod;

    private BigDecimal amount;
    private Integer currency;

    @NotNull @Min(1)
    private Integer state;

    private String tags;

    private LocalDateTime paidAt;
}
