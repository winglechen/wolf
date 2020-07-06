package net.onionpay.gateway.pg.dto;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.DTO;

/**
 * net.onionpay.gateway.pg.dto
 *
 * @author Wingle
 * @since 2020/7/4 9:28 下午
 **/
@Data
public class PaymentStatusDTO implements DTO {
    private String paymentNo;
    private Long payerId;
    private Long payeeId;

    private String stateCode;
    private Integer state;

    private String returnUrl;
}
