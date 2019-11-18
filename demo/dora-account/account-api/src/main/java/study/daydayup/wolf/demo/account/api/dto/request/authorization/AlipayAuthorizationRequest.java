package study.daydayup.wolf.demo.account.api.dto.request.authorization;

import lombok.Data;

@Data
public class AlipayAuthorizationRequest extends AuthorizationRequest {

    private String code;
}
