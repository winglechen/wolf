package study.daydayup.wolf.business.pay.api.domain.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

/**
 * study.daydayup.wolf.business.pay.api.domain.entity
 *
 * @author Wingle
 * @since 2020/2/26 4:07 下午
 **/
@Data
public class Payment implements Model {
    private Long payerId;
    private String payerName;
    private Long payeeId;
    private String payeeName;

    private String tradeNo;
    private String paymentNo;
    private String outTradeNo;
}
