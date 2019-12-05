package study.daydayup.wolf.business.account.api.entity;

import lombok.Data;
import study.daydayup.wolf.business.account.api.entity.Account;

import java.util.Date;

/**
 * study.daydayup.wolf.business.account.api.entity.license
 *
 * @author Wingle
 * @since 2019/9/27 5:29 PM
 **/
@Data
public class License {
    private long accountId;

    private String clientId;
    private String scope;

    private String accessToken;
    private Date expiredAt;
    private String refreshToken;
    private Date refreshExpiredAt;

}
