package study.daydayup.wolf.business.pay.api.dto.base;

import lombok.Data;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.framework.layer.api.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * study.daydayup.wolf.business.pay.api.dto
 *
 * @author Wingle
 * @since 2020/2/27 3:54 下午
 **/
@Data
public class PaymentCreateResponse extends Attachment implements Response {
    private Integer paymentMethod;

    private String paymentNo;
    private BigDecimal amount;
    private ObjectMap attachment;

    public PaymentCreateResponse() {
        attachment = new ObjectMap();
    }


}
