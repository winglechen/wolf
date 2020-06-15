package study.daydayup.wolf.business.account.biz.service;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.account.biz.service
 *
 * @author Wingle
 * @since 2019/12/9 6:23 下午
 **/
public interface VerifyCodeService {
    void send(String mobile, String code, LocalDateTime expiredAt);
    void send(String mobile, String code, LocalDateTime expiredAt, Long orgId);
    boolean verify(String mobile, String code);
}
