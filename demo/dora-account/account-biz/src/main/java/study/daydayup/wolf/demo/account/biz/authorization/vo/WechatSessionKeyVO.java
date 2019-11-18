package study.daydayup.wolf.demo.account.biz.authorization.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WechatSessionKeyVO {

    private String sessionKey;

    private String openId;

    private String unionId;

}
