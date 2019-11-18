package study.daydayup.wolf.demo.account.biz.authorization.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountWechatVO {

    private Long uid;

    private String openId;

    private String mpOpenId;

    private String unionId;
}
