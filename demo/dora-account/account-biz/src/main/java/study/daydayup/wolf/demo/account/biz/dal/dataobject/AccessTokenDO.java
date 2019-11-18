package study.daydayup.wolf.demo.account.biz.dal.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class AccessTokenDO {

    private Long id;

    private Long uid;

    private String clientId;

    private String accessTokenValue;

    private String refreshTokenValue;

    private Date expiredAt;

    private Integer isDelete;

    private Integer source;

    private Date createdAt;

    private Date updatedAt;
}
