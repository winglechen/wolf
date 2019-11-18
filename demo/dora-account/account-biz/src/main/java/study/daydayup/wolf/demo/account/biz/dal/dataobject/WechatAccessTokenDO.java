package study.daydayup.wolf.demo.account.biz.dal.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class WechatAccessTokenDO {

    private Long id;

    private Long uid;

    private String openId;

    private String unionId;

    private String accessToken;

    private String refreshToken;

    private Integer expiresIn;

    private Date expiredAt;

    private String scope;

    private Integer isDelete;

    private Date createdAt;

    private Date updatedAt;

}