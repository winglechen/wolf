package study.daydayup.wolf.demo.account.biz.authorization.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ThirdAuthenticationVO {

    private String openId;

    private String accessToken;

    private Integer expiresIn;

}
