package study.daydayup.wolf.business.pay.biz.service.india.razorpay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.dto
 *
 * @author Wingle
 * @since 2020/3/23 3:13 上午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactEpiRequest implements Request {
    private String name;
    private String type;
    private String referenceId;
}
