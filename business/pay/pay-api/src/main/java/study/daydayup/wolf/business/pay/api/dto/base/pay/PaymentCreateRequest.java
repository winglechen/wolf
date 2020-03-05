package study.daydayup.wolf.business.pay.api.dto.base.pay;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.pay.api.dto
 *
 * @author Wingle
 * @since 2020/2/27 3:53 下午
 **/
@Data
@Builder
public class PaymentCreateRequest implements Request {
    private Integer paymentMethod;

    private String tradeNo;
    private boolean doublePayCheck;

    @NotNull @Min(1)
    private Long payerId;
    private String payerName;
    @NotNull @Min(1)
    private Long payeeId;
    private String payeeName;

    @NotNull @DecimalMin("0.01")
    private BigDecimal amount;
    @NotNull
    private Integer currency;

    private Long goodsId;
    private String goodsName;
    private String goodsDesc;

    private String tags;
}
