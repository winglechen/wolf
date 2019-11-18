package study.daydayup.wolf.demo.account.biz.authorization.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WechatMpOAuthResponseVO {

    private String openId;

    private String unionId;

    private String accessToken;

    private Integer expiresIn;

    private String refreshToken;

    private String scope;
}
