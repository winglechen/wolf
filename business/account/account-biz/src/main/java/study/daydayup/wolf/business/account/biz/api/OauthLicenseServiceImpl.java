package study.daydayup.wolf.business.account.biz.api;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.account.api.dto.request.LicenseRequest;
import study.daydayup.wolf.business.account.api.entity.License;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.service.licenser.OauthLicenseService;
import study.daydayup.wolf.business.account.biz.dal.dao.AccessTokenDAO;
import study.daydayup.wolf.business.account.biz.dal.dataobject.AccessTokenDO;
import study.daydayup.wolf.business.account.biz.service.AccessTokenService;
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
    private AccessTokenService accessTokenService;

    @Override
    public OauthLicense findByAccessToken(String accessToken) {
        if (null == accessToken || "" == accessToken) {
            return null;
        }

        License license = accessTokenService.findByToken(accessToken);
        return licenseToOauth(license);
    }

    @Override
    public void expire(String accessToken) {
        if (null == accessToken || "" == accessToken) {
            return ;
        }

    }

    @Override
    public OauthLicense findByRefreshToken(String refreshToken) {
        return null;
    }

    @Override
    public OauthLicense grant(@Valid LicenseRequest request) {
        License license = accessTokenService.create(request);

        return licenseToOauth(license);
    }

    @Override
    public void create(OauthLicense license) {

    }

    @Override
    public void refresh(String refreshToken, Date expiredAt) {

    }

    private OauthLicense licenseToOauth(License license) {
        if (null == license) {
            return null;
        }

        OauthLicense oauthLicense = new OauthLicense();
        BeanUtils.copyProperties(license, oauthLicense);

        return oauthLicense;
    }

}
