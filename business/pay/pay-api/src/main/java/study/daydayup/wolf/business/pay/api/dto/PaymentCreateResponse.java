package study.daydayup.wolf.business.pay.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * study.daydayup.wolf.business.pay.api.dto
 *
 * @author Wingle
 * @since 2020/2/27 3:54 下午
 **/
@Data
public class PaymentCreateResponse {
    private Integer paymentMethod;

    private String paymentNo;
    private BigDecimal amount;
    private Long longAmount;

    private Map<String, Object> attachment;
}
