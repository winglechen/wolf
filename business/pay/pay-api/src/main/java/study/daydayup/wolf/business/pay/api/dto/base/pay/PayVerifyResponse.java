package study.daydayup.wolf.business.pay.api.dto.base.pay;

import study.daydayup.wolf.business.pay.api.dto.base.pay.Attachment;
import study.daydayup.wolf.framework.layer.api.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * study.daydayup.wolf.business.pay.api.dto
 *
 * @author Wingle
 * @since 2020/2/27 4:43 下午
 **/
public class PayVerifyResponse extends Attachment implements Response {
    private Integer paymentMethod;
    private BigDecimal amount;
    private Boolean success;
    private LocalDateTime updateAt;
}
