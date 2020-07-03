package net.onionpay.gateway.pg.dto;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.DTO;

import javax.validation.constraints.NotBlank;

/**
 * net.onionpay.gateway.pg.dto
 *
 * @author Wingle
 * @since 2020/7/3 12:05 下午
 **/
@Data
public class CheckoutRequest implements DTO {
    @NotBlank
    private String token;
    @NotBlank
    private String paymentMode;
}
