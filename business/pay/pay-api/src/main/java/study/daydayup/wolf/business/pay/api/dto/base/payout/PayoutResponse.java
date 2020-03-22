package study.daydayup.wolf.business.pay.api.dto.base.payout;

import lombok.Data;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.framework.layer.api.Response;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.pay.api.dto.base
 *
 * @author Wingle
 * @since 2020/2/29 11:44 下午
 **/
@Data
public class PayoutResponse implements Response {
    private Integer paymentMethod;

    private String paymentNo;
    private BigDecimal amount;
    private ObjectMap attachment;

    public PayoutResponse() {
        attachment = new ObjectMap();
    }
}
