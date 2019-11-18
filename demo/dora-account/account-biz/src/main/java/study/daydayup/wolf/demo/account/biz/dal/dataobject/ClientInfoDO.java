package study.daydayup.wolf.demo.account.biz.dal.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class ClientInfoDO {

    private Integer id;

    private String clientId;

    /**
	* Client类型，0 短信验证码，1 微信app登录，2 微信小程序

	*/
    private Integer clientType;

    private String secret;

    private String wechatAppId;

    private String wechatSecret;

    private Date createdAt;

    private Date updatedAt;

}