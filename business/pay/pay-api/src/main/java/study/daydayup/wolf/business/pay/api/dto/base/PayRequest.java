package study.daydayup.wolf.business.pay.api.dto.base;

import lombok.NonNull;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.Min;
import java.util.HashMap;

/**
 * study.daydayup.wolf.business.pay.api.dto
 *
 * @author Wingle
 * @since 2020/2/27 4:42 下午
 **/
public class PayRequest extends Attachment implements Request {
    private Integer paymentMethod;
    @NonNull @Min(1)
    private Long payeeId;
    @NonNull @Min(1)
    private Long payerId;


    public PayRequest() {
        attachment = new HashMap<>(8);
    }

}
