package study.daydayup.wolf.business.pay.api.dto;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.pay.api.dto
 *
 * @author Wingle
 * @since 2020/2/27 3:53 下午
 **/
@Data
public class PaymentCreateRequest implements Request {
    private Integer paymentMethod;

    private String tradeNo;
    private Long payerId;
    private String payerName;
    private Long payeeId;
    private String payeeName;

    private BigDecimal amount;
    private Integer currency;

    private Long goodsId;
    private Long goodsName;
    private Long goodsDesc;

    private String tags;
}
