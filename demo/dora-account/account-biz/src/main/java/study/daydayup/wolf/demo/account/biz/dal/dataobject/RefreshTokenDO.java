package study.daydayup.wolf.demo.account.biz.dal.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class RefreshTokenDO {

    private Long id;

    private Long uid;

    private String clientId;

    private String refreshTokenValue;

    private Date expiredAt;

    private Integer isDelete;

    private Date createdAt;

    private Date updatedAt;
}
