package study.daydayup.wolf.business.account.auth.agent.controller;

import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.auth.agent.Session;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.account.auth.agent.controller
 * base auth controller : cookie | header | token -> handling
 *
 * @author Wingle
 * @since 2019/12/4 5:47 下午
 **/
public class AuthController {
    @Resource
    private Session session;

    protected void saveLicenseToSession(OauthLicense license) {

    }

    protected boolean isLogin() {
        return false;
    }
}
