package study.daydayup.wolf.business.account.biz.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.account.api.dto.request.LicenseRequest;
import study.daydayup.wolf.business.account.api.entity.License;
import study.daydayup.wolf.business.account.biz.dal.dao.AccessTokenDAO;
import study.daydayup.wolf.business.account.biz.dal.dataobject.AccessTokenDO;
import study.daydayup.wolf.business.account.biz.service.AccessTokenService;
import study.daydayup.wolf.common.util.DateUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * study.daydayup.wolf.business.account.biz.service.impl
 *
 * @author Wingle
 * @since 2019/12/9 3:51 下午
 **/
@Component
public class AccessTokenServiceImpl implements AccessTokenService {
    @Resource
    private AccessTokenDAO accessTokenDAO;

    @Override
    public License create(@Valid LicenseRequest request) {
        AccessTokenDO accessTokenDO = accessTokenDAO.selectByAccessToken(request.getToken());
        if (accessTokenDO == null) {
            return createLicense(request, true);
        }

        Long requestAccountId = request.getAccountId();
        Long dbAccountId = accessTokenDO.getAccountId();
        if (dbAccountId == null) {
            return createLicense(request, true);
        }

        if (requestAccountId.equals(dbAccountId)) {
            refreshById(accessTokenDO.getId(), request.getExpiredIn());
            return doToLicense(accessTokenDO);
        }

        //accessToken conflict
        return createLicense(request, false);
    }


    @Override
    public License findByToken(@NotBlank String accessToken) {
        if (null == accessToken || "".equals(accessToken)) {
            return null;
        }
        AccessTokenDO accessTokenDO = accessTokenDAO.selectByAccessToken(accessToken);
        return doToLicense(accessTokenDO);
    }

    @Override
    public void expire(@NotBlank String accessToken) {
        expire(accessToken, new Date());
    }

    @Override
    public void expire(@NotBlank String accessToken, Date expiredAt) {
        if (null == accessToken || "".equals(accessToken)) {
            return ;
        }

        if (null == expiredAt) {
            expiredAt = new Date();
        }

        accessTokenDAO.updateExpiredAtByAccessToken(accessToken, expiredAt);
    }

    private long create(AccessTokenDO accessTokenDO) {
        return accessTokenDAO.insertSelective(accessTokenDO);
    }

    @Override
    public void refresh(@NotBlank String refreshToken, int seconds) {
        if (null == refreshToken || "".equals(refreshToken) ) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        Date updatedAt = DateUtil.asDate(now);
        Date expiredAt = formatExpireIn(now, seconds);
        accessTokenDAO.updateExpiredAtByRefreshToken(refreshToken, expiredAt, updatedAt);
    }

    @Override
    public void refreshById(long id, int seconds) {
        LocalDateTime now = LocalDateTime.now();

        Date updatedAt = DateUtil.asDate(now);
        Date expiredAt = formatExpireIn(now, seconds);
        accessTokenDAO.updateExpiredAtById(id, expiredAt, updatedAt);
    }

    @Override
    public void refreshByAccountId(long accountId, int seconds) {
        LocalDateTime now = LocalDateTime.now();

        Date updatedAt = DateUtil.asDate(now);
        Date expiredAt = formatExpireIn(now, seconds);
        accessTokenDAO.updateExpiredAtByAccountId(accountId, expiredAt, updatedAt);
    }

    @Override
    public long existToken(@NotBlank String token) {
        if (null == token || "".equals(token)) {
            return 0;
        }

        Long id = accessTokenDAO.selectIdByAccessToken(token);
        if (null != id) {
            return (long)id;
        }

        return 0;
    }

    private License createLicense(LicenseRequest request, boolean useRequestToken) {
        if (!useRequestToken) {
            request.setToken(null);
        }

        AccessTokenDO accessTokenDO = new AccessTokenDO();
        initTokenDO(accessTokenDO, request);
        formatTokens(accessTokenDO, request);
        formatCreateDates(accessTokenDO, request);
        create(accessTokenDO);

        return doToLicense(accessTokenDO);
    }

    private License doToLicense(AccessTokenDO accessTokenDO) {
        if (null == accessTokenDO) {
            return  null;
        }

        License license = new License();
        BeanUtils.copyProperties(accessTokenDO, license);

        return license;
    }

    private void initTokenDO(AccessTokenDO accessTokenDO, LicenseRequest request) {
        accessTokenDO.setAccountId(request.getAccountId());
        accessTokenDO.setScope(request.getScope());
        accessTokenDO.setClientId(request.getClientId());
    }

    private void formatTokens(AccessTokenDO accessTokenDO, LicenseRequest request) {
        String accessToken = request.getToken();
        if (null == accessToken) {
            accessToken = createToken();
        }

        accessTokenDO.setAccessToken(accessToken);
        accessTokenDO.setRefreshToken(createToken());
    }

    private void formatCreateDates(AccessTokenDO accessTokenDO, LicenseRequest request) {
        LocalDateTime now = LocalDateTime.now();
        accessTokenDO.setCreatedAt(DateUtil.asDate(now));

        LocalDateTime expiredAt = now.plusSeconds(request.getExpiredIn());
        accessTokenDO.setExpiredAt(DateUtil.asDate(expiredAt));

        LocalDateTime refreshExpiredAt = expiredAt.plusSeconds(request.getRefreshExpiredIn());
        accessTokenDO.setRefreshExpiredAt(DateUtil.asDate(refreshExpiredAt) );
    }

    private Date formatExpireIn(LocalDateTime now, int seconds) {
        return DateUtil.asDate( now.plusSeconds(seconds) );
    }

    private String createToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
