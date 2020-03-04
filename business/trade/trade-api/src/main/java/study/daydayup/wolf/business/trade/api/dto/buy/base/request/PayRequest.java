package study.daydayup.wolf.business.trade.api.dto.buy.base.request;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.domain.enums.order.PaymentMethodEnum;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.NotBlank;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy.base.request
 *
 * @author Wingle
 * @since 2019/10/9 1:43 下午
 **/
@Data
public class PayRequest implements Request {
    private TradeId tradeId;

    /**
     * @see PaymentMethodEnum
     */
    private Integer paymentMethod;
    @NotBlank
    private String tradeNo;

    private Integer installmentNo;
    private Integer installmentNum;

}
