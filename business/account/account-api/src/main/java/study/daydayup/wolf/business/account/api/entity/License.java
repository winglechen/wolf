package study.daydayup.wolf.business.account.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.account.api.entity.license
 *
 * @author Wingle
 * @since 2019/9/27 5:29 PM
 **/
@Data
public class License implements Serializable {
    protected Long accountId;
    protected String account;
    protected Integer accountType;
    protected String clientId;
    protected String scope;

    protected String accessToken;
    protected LocalDateTime expiredAt;
    protected String refreshToken;
    protected LocalDateTime refreshExpiredAt;

}
