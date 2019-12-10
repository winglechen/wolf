package study.daydayup.wolf.business.account.biz.service;

import study.daydayup.wolf.business.account.api.dto.request.LicenseRequest;
import study.daydayup.wolf.business.account.api.entity.License;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * study.daydayup.wolf.business.account.biz.service
 *
 * @author Wingle
 * @since 2019/12/9 3:49 下午
 **/
public interface AccessTokenService {
    License create(@Valid LicenseRequest request);
    License findByToken(@NotBlank String accessToken);
    void expire(@NotBlank String accessToken);
    void refresh(@NotBlank String refreshToken, int seconds);
    void refreshById(long id, int seconds);
    void refreshByAccountId(long accountId, int seconds);
    long existToken(@NotBlank String token);
}
