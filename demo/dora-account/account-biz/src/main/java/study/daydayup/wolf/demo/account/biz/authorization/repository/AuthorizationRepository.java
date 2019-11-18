package study.daydayup.wolf.demo.account.biz.authorization.repository;

import study.daydayup.wolf.demo.account.api.exception.BindWechatUserException;
import study.daydayup.wolf.demo.account.biz.authorization.entity.VerifyCode;
import study.daydayup.wolf.demo.account.biz.authorization.entity.licensor.AccessToken;
import study.daydayup.wolf.demo.account.biz.authorization.entity.licensor.RefreshToken;
import study.daydayup.wolf.demo.account.biz.authorization.facade.AccountFacade;
import study.daydayup.wolf.demo.account.biz.authorization.facade.ClientFacade;
import study.daydayup.wolf.demo.account.biz.authorization.facade.UserFacade;
import study.daydayup.wolf.demo.account.biz.authorization.facade.WechatAuthorizationFacade;
import study.daydayup.wolf.demo.account.biz.authorization.vo.*;
import study.daydayup.wolf.demo.account.biz.dal.dao.*;
import study.daydayup.wolf.demo.account.biz.dal.dataobject.*;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;
import study.daydayup.wolf.demo.account.biz.authorization.vo.*;
import study.daydayup.wolf.demo.account.biz.dal.dao.*;
import study.daydayup.wolf.demo.account.biz.dal.dataobject.*;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class AuthorizationRepository {

    @Resource
    private AccountWechatDAO accountWechatDAO;

    @Resource
    private AccessTokenDAO accessTokenDAO;

    @Resource
    private RefreshTokenDAO refreshTokenDAO;

    @Resource
    private WechatAccessTokenDAO wechatAccessTokenDAO;

    @Resource
    private VerifyCodeDAO verifyCodeDAO;

    @Resource
    private WechatSessionKeyDAO wechatSessionKeyDAO;

    @Resource
    private WechatAuthorizationFacade wechatAuthorizationFacade;

    @Resource
    private AccountFacade accountFacade;

    @Resource
    private ClientFacade clientFacade;

    @Resource
    private UserFacade userFacade;

    public WechatMpOAuthResponseVO authorize(Integer authorizationType, String authorizationCode) {
        return wechatAuthorizationFacade.authorize(authorizationType, authorizationCode);
    }

    public WechatSessionKeyVO getSessionKey(String authorizationCode) {
        return wechatAuthorizationFacade.getSessionKey(authorizationCode);
    }

    public WechatMpUserInfoResponseVO getWechatUserInfo(String accessToken, String openId) {
        return wechatAuthorizationFacade.getWechatUserInfo(accessToken, openId);
    }

    public AccountVO createAccount(WechatMpOAuthResponseVO wechatMpOAuthResponseVO, WechatMpUserInfoResponseVO wechatMpUserInfoResponseVO) {
        return accountFacade.createAccount(wechatMpOAuthResponseVO, wechatMpUserInfoResponseVO);
    }

    public AccountVO createAccount(WechatSessionKeyVO wechatSessionKeyVO) {
        return accountFacade.createAccount(wechatSessionKeyVO);
    }


    public AccountWechatVO getAccountWechatByOpenId(String openId) {
        AccountWechatDO accountWechatDO = accountWechatDAO.selectOneByOpenId(openId);
        if (accountWechatDO == null) {
            return null;
        }
        return new AccountWechatVO(accountWechatDO.getUid(), accountWechatDO.getOpenId(), accountWechatDO.getMpOpenId(), accountWechatDO.getUnionId());
    }

    public AccountWechatVO getAccountWechatByUnionId(String unionId) {
        AccountWechatDO accountWechatDO = accountWechatDAO.selectOneByUnionId(unionId);
        if (accountWechatDO == null) {
            return null;
        }
        return new AccountWechatVO(accountWechatDO.getUid(), accountWechatDO.getOpenId(), accountWechatDO.getMpOpenId(), accountWechatDO.getUnionId());
    }

    public ClientVO getClient(String clientId) {
        return clientFacade.getClient(clientId);
    }

    public AccountWechatVO createAccountWechat(Long userId, String openId, String mpOpenId, String unionId) {
        AccountWechatDO accountWechatDO = new AccountWechatDO();
        accountWechatDO.setUid(userId);
        accountWechatDO.setOpenId(openId);
        accountWechatDO.setMpOpenId(mpOpenId);
        accountWechatDO.setUnionId(unionId);
        accountWechatDO.setVersion(1);
        accountWechatDO.setDeleteFlag(0);
        accountWechatDO.setLastEditor(0L);
        accountWechatDO.setCreateTime(new Date());
        accountWechatDO.setUpdateTime(new Date());
        int insert = accountWechatDAO.insert(accountWechatDO);
        if (insert <= 0) {
            throw new BindWechatUserException("绑定微信账号失败");
        }
        return new AccountWechatVO(accountWechatDO.getUid(), accountWechatDO.getOpenId(), accountWechatDO.getMpOpenId(), accountWechatDO.getUnionId());
    }

    public boolean updateMpOpenIdByUidAndUnionId(Long uid, String unionId, String mpOpenId) {
        int update = accountWechatDAO.updateMpOpenIdByUidAndUnionId(mpOpenId, uid, unionId);
        return update > 0;
    }

    public boolean updateOpenIdByUidAndUnionId(Long uid, String unionId, String openId) {
        int update = accountWechatDAO.updateOpenIdByUidAndUnionId(openId, uid, unionId);
        return update > 0;
    }

    public Boolean createAccessToken(AccessToken accessToken) {
        AccessTokenDO accessTokenDO = new AccessTokenDO();
        accessTokenDO.setUid(accessToken.getAccountVO().getUid());
        accessTokenDO.setAccessTokenValue(accessToken.getAccessToken());
        accessTokenDO.setClientId(accessToken.getClientVO().getClientId());
        accessTokenDO.setExpiredAt(accessToken.getExpiredVO().getExpiredAt());
        accessTokenDO.setSource(0);
        accessTokenDO.setRefreshTokenValue(accessToken.getRefreshToken().getRefreshToken());
        accessTokenDO.setCreatedAt(new Date());
        accessTokenDO.setUpdatedAt(new Date());
        accessTokenDO.setIsDelete(0);
        int insert = accessTokenDAO.insert(accessTokenDO);
        return insert > 0;
    }

    public AccessToken getAccessToken(String accessTokenValue) {
        AccessTokenDO accessTokenDO = accessTokenDAO.getByAccessTokenValue(accessTokenValue);
        if (accessTokenDO == null) {
            return null;
        }
        AccessToken accessToken = new AccessToken(this);
        accessToken.setAccountVO(new AccountVO(accessTokenDO.getUid()));
        accessToken.setAccessToken(accessTokenDO.getAccessTokenValue());
        accessToken.setClientVO(new ClientVO(accessTokenDO.getClientId()));
        accessToken.setExpiredVO(new DateExpiredVO(accessTokenDO.getExpiredAt()));
        accessToken.setRefreshToken(getRefreshToken(accessTokenDO.getRefreshTokenValue()));
        return accessToken;
    }

    public Boolean createRefreshToken(RefreshToken refreshToken) {
        RefreshTokenDO refreshTokenDO = new RefreshTokenDO();
        refreshTokenDO.setClientId(refreshToken.getClientVO().getClientId());
        refreshTokenDO.setUid(refreshToken.getAccountVO().getUid());
        refreshTokenDO.setRefreshTokenValue(refreshToken.getRefreshToken());
        refreshTokenDO.setExpiredAt(refreshToken.getExpiredVO().getExpiredAt());
        refreshTokenDO.setIsDelete(0);
        refreshTokenDO.setCreatedAt(new Date());
        refreshTokenDO.setUpdatedAt(new Date());
        int insert = refreshTokenDAO.insert(refreshTokenDO);
        return insert > 0;
    }

    public Boolean updateRefreshToken(RefreshToken refreshToken) {
        int update = refreshTokenDAO.updateExpiredAtByRefreshTokenValue(refreshToken.getExpiredVO().getExpiredAt(), refreshToken.getRefreshToken());
        return update > 0;
    }

    public RefreshToken getRefreshToken(String refreshTokenValue) {
        RefreshTokenDO refreshTokenDO = refreshTokenDAO.getByRefreshTokenValue(refreshTokenValue);
        if (refreshTokenDO == null) {
            return null;
        }
        RefreshToken refreshToken = new RefreshToken(this);
        refreshToken.setAccountVO(new AccountVO(refreshTokenDO.getUid()));
        refreshToken.setClientVO(new ClientVO(refreshTokenDO.getClientId()));
        refreshToken.setExpiredVO(new DateExpiredVO(refreshTokenDO.getExpiredAt()));
        refreshToken.setRefreshToken(refreshTokenDO.getRefreshTokenValue());
        return refreshToken;
    }

    public Boolean createWechatAccessToken(AccountWechatVO accountWechatVO, WechatMpOAuthResponseVO wechatMpOAuthResponseVO) {
        WechatAccessTokenDO wechatAccessTokenDO = new WechatAccessTokenDO();
        wechatAccessTokenDO.setUid(accountWechatVO.getUid());
        wechatAccessTokenDO.setOpenId(wechatMpOAuthResponseVO.getOpenId());
        wechatAccessTokenDO.setUnionId(wechatMpOAuthResponseVO.getUnionId());
        wechatAccessTokenDO.setAccessToken(wechatMpOAuthResponseVO.getAccessToken());
        wechatAccessTokenDO.setRefreshToken(wechatMpOAuthResponseVO.getRefreshToken());
        wechatAccessTokenDO.setExpiresIn(wechatMpOAuthResponseVO.getExpiresIn());
        wechatAccessTokenDO.setExpiredAt(DateUtils.addSeconds(new Date(), wechatMpOAuthResponseVO.getExpiresIn()));
        wechatAccessTokenDO.setScope(wechatMpOAuthResponseVO.getScope());
        wechatAccessTokenDO.setIsDelete(0);
        wechatAccessTokenDO.setCreatedAt(new Date());
        wechatAccessTokenDO.setUpdatedAt(new Date());

        int insert = wechatAccessTokenDAO.insert(wechatAccessTokenDO);
        return insert > 0;
    }

    public Boolean saveUserDetail(AccountWechatVO accountWechat, WechatMpUserInfoResponseVO wechatMpUserInfoResponse) {
        return userFacade.saveUserDetail(accountWechat, wechatMpUserInfoResponse);
    }

    public VerifyCode getVerifyCodeByMobileAndCode(String mobile, String code) {
        VerifyCodeDO verifyCodeDO = verifyCodeDAO.getByMobileAndCode(mobile, code);
        if (verifyCodeDO == null) {
            return null;
        }
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setId(verifyCodeDO.getId());
        verifyCode.setMobile(verifyCodeDO.getMobile());
        verifyCode.setCode(verifyCodeDO.getCode());
        verifyCode.setExpiredVO(new DateExpiredVO(verifyCodeDO.getExpiredAt()));
        verifyCode.setCreateAt(verifyCodeDO.getCreatedAt());
        return verifyCode;
    }

    public AccountVO gitWithCreateAccount(String mobile) {
        return accountFacade.getWithCreateAccount(mobile);
    }


    public boolean getWithCreateWechatSessionKey(AccountWechatVO accountWechatVO, WechatSessionKeyVO wechatSessionKeyVO) {
        WechatSessionKeyDO wechatSessionKeyDO = wechatSessionKeyDAO.getByUidAndSessionKey(accountWechatVO.getUid(), wechatSessionKeyVO.getSessionKey());
        if (wechatSessionKeyDO != null) {
            return true;
        }

        wechatSessionKeyDO = new WechatSessionKeyDO();
        wechatSessionKeyDO.setUid(accountWechatVO.getUid());
        wechatSessionKeyDO.setOpenId(accountWechatVO.getMpOpenId());
        wechatSessionKeyDO.setUnionId(accountWechatVO.getUnionId());
        wechatSessionKeyDO.setSessionKey(wechatSessionKeyVO.getSessionKey());
        wechatSessionKeyDO.setCreatedAt(new Date());
        wechatSessionKeyDO.setUpdatedAt(new Date());
        wechatSessionKeyDO.setIsDelete(0);
        int insert = wechatSessionKeyDAO.insert(wechatSessionKeyDO);
        return insert > 0;
    }

}
