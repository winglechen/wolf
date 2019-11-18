package study.daydayup.wolf.demo.account.api.dto.request.auth;

import lombok.Data;

@Data
public class AlipayRequest extends AuthRequest {

    private String code;
}
