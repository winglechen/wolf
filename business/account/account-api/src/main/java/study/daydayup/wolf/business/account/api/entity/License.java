package study.daydayup.wolf.business.account.api.entity;

import lombok.Data;
import study.daydayup.wolf.business.account.api.entity.Account;

import java.io.Serializable;
import java.util.Date;

/**
 * study.daydayup.wolf.business.account.api.entity.license
 *
 * @author Wingle
 * @since 2019/9/27 5:29 PM
 **/
@Data
public class License implements Serializable {
    protected Long accountId;
    protected String clientId;
    protected String scope;

    protected String accessToken;
    protected Date expiredAt;
    protected String refreshToken;
    protected Date refreshExpiredAt;

}
