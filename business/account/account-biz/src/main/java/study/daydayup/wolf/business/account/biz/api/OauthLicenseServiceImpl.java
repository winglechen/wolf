package study.daydayup.wolf.business.account.biz.api;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.account.api.dto.request.LicenseRequest;
import study.daydayup.wolf.business.account.api.entity.License;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.service.licenser.OauthLicenseService;
import study.daydayup.wolf.business.account.biz.dal.dao.AccessTokenDAO;
import study.daydayup.wolf.business.account.biz.dal.dataobject.AccessTokenDO;
import study.daydayup.wolf.common.util.DateUtil;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * study.daydayup.wolf.business.account.biz.api
 *
 * @author Wingle
 * @since 2019/12/5 7:20 下午
 **/
@RpcService(protocol = "dubbo")
public class OauthLicenseServiceImpl implements OauthLicenseService {
    @Resource
    private AccessTokenDAO accessTokenDAO;

    @Override
    public OauthLicense findByAccessToken(String accessToken) {
        return null;
    }

    @Override
    public OauthLicense findByRefreshToken(String refreshToken) {
        return null;
    }

    @Override
    public OauthLicense grant(@Valid LicenseRequest request) {
        AccessTokenDO accessTokenDO = new AccessTokenDO();
        initTokenDO(accessTokenDO, request);
        formatTokens(accessTokenDO, request);
        formatDates(accessTokenDO, request);

        accessTokenDAO.insertSelective(accessTokenDO);

        return doToLicense(accessTokenDO);
    }

    @Override
    public void create(OauthLicense license) {

    }

    @Override
    public void refresh(String refreshToken, Date expiredAt) {

    }

    private OauthLicense doToLicense(AccessTokenDO accessTokenDO) {
        OauthLicense license = new OauthLicense();
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

    private void formatDates(AccessTokenDO accessTokenDO, LicenseRequest request) {
        LocalDateTime now = LocalDateTime.now();
        accessTokenDO.setCreatedAt(DateUtil.asDate(now));

        LocalDateTime expiredAt = now.plusSeconds(request.getExpiredIn());
        accessTokenDO.setExpiredAt(DateUtil.asDate(expiredAt));

        LocalDateTime refreshExpiredAt = now.plusSeconds(request.getRefreshExpiredIn());
        accessTokenDO.setRefreshExpiredAt(DateUtil.asDate(refreshExpiredAt) );
    }

    private String createToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
