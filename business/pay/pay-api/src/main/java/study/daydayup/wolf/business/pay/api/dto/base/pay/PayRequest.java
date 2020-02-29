package study.daydayup.wolf.business.pay.api.dto.base.pay;

import lombok.Data;
import lombok.NonNull;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.Min;

/**
 * study.daydayup.wolf.business.pay.api.dto
 *
 * @author Wingle
 * @since 2020/2/27 4:42 下午
 **/
@Data
public class PayRequest implements Request {
    private Integer paymentMethod;
    @NonNull @Min(1)
    private Long payeeId;
    @NonNull @Min(1)
    private Long payerId;
    @NonNull
    private ObjectMap attachment;


    public PayRequest() {
        attachment = new ObjectMap();
    }

}
