package study.daydayup.wolf.business.account.biz.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import study.daydayup.wolf.business.account.api.dto.request.LicenseRequest;
import study.daydayup.wolf.business.account.api.dto.request.SmsCodeRequest;
import study.daydayup.wolf.business.account.api.dto.request.SmsRequest;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.enums.AccountTypeEnum;
import study.daydayup.wolf.business.account.api.exception.InvalidVerifyCodeException;
import study.daydayup.wolf.business.account.api.service.AccountService;
import study.daydayup.wolf.business.account.api.service.auth.SmsAuthService;
import study.daydayup.wolf.business.account.api.service.licenser.OauthLicenseService;
import study.daydayup.wolf.business.account.biz.dal.dao.AccountDAO;
import study.daydayup.wolf.business.account.biz.dal.dataobject.AccountDO;
import study.daydayup.wolf.business.account.biz.service.VerifyCodeService;
import study.daydayup.wolf.common.util.time.DateUtil;
import study.daydayup.wolf.framework.exception.LocaleNotFoundException;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.util.LocaleUtil;
import study.daydayup.wolf.middleware.notice.agent.service.SMSAgent;
import study.daydayup.wolf.middleware.notice.api.config.SMSSendConfig;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * study.daydayup.wolf.business.account.biz.api
 *
 * @author Wingle
 * @since 2019/12/5 6:02 下午
 **/
@Slf4j
@RpcService
public class SmsAuthServiceImpl implements SmsAuthService {
    @Resource
    private AccountDAO accountDAO;
    @Value("${wolf.sms.mockMode:false}")
    private boolean mockMode;
    private static final String OTP_KEY = "account.login.otp";
    // "OnionWallet"
    private static final String BRAND_NAME = "RupeeWallet";
    private static final String TEST_OTP = "778899";

    @Resource
    private AccountService accountService;
    @Reference
    private OauthLicenseService licenseService;
    @Resource
    private VerifyCodeService verifyCodeService;
    @Resource
    private SMSAgent smsAgent;

    @Override
    public Result<Long> register(SmsRequest request) {
        return Result.ok(0L);
    }

    @Override
    public Result<OauthLicense> login(SmsRequest request) {
        return Result.ok(null);
    }

    @Override
    public Result<OauthLicense> registerAndLogin(SmsRequest request) {
        if (!verifyCodeService.verify(request.getMobile(), request.getCode())) {
            throw new InvalidVerifyCodeException();
        }

        long accountId = accountService.existByAccount(request.getMobile());
        if (0 == accountId) {
            accountId = createSmsAccount(request.getMobile(), request.getSource());
        }

        LicenseRequest licenseRequest = new LicenseRequest();
        BeanUtils.copyProperties(request, licenseRequest);
        licenseRequest.setAccountId(accountId);

        //TODO MOVE to accountService
        OauthLicense license = licenseService.grant(licenseRequest);
        return Result.ok(license);
    }

    @Override
    public Result<Object> sendCode(SmsCodeRequest request) {
        String mobile = request.getMobile();
        String code = createCode();
        Date expiredAt = DateUtil.secondsLater(request.getExpiredIn());

        verifyCodeService.send(mobile, code, expiredAt);
        sendSms(mobile, code, request.getOrgId());

        return Result.ok();
    }

    private long createSmsAccount(String mobile, String source) {
        if(null == mobile) {
            return 0;
        }

        AccountDO accountDO = new AccountDO();
        accountDO.setAccount(mobile);
        accountDO.setSource(source);
        accountDO.setAccountType((byte) AccountTypeEnum.MOBILE.getCode());
        accountDO.setCreatedAt(new Date());

        accountDAO.insertSelective(accountDO);
        if (null == accountDO.getId()) {
            return 0;
        }
        return accountDO.getId();
    }

    private void sendSms(String mobile, String code, Long orgId) {
        if (mockMode) {
            return;
        }

        String[] args = new String[]{code};
        String msg = LocaleUtil.get(OTP_KEY, args);
        if (msg == null) {
            throw new LocaleNotFoundException(OTP_KEY);
        }

        SMSSendConfig config = SMSSendConfig.builder()
                .orgId(orgId)
                .build();
        smsAgent.toIndia(mobile, msg, config);
    }

    private String createCode() {
        if (mockMode) {
            return TEST_OTP;
        }

        int num = ThreadLocalRandom.current().nextInt(100000, 999999);
        return Integer.toString(num);
    }
}
