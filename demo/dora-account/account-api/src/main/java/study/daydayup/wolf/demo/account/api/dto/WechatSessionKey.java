package study.daydayup.wolf.demo.account.api.dto;

import lombok.Data;

@Data
public class WechatSessionKey {

    private Long uid;

    private String openId;

    private String miniOpenId;

    private String unionId;

    private String sessionKey;
}
