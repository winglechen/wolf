package study.daydayup.wolf.business.account.biz.service.impl;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.account.biz.dal.dao.VerifyCodeDAO;
import study.daydayup.wolf.business.account.biz.dal.dataobject.VerifyCodeDO;
import study.daydayup.wolf.business.account.biz.service.VerifyCodeService;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.account.biz.service.impl
 *
 * @author Wingle
 * @since 2019/12/9 6:24 下午
 **/
@Component
public class VerifyCodeServiceImpl implements VerifyCodeService {
    @Resource
    private VerifyCodeDAO verifyCodeDAO;

    @Override
    public void send(String mobile, String code, LocalDateTime expiredAt) {
        send(mobile, code, expiredAt, null);
    }

    @Override
    public void send(String mobile, String code, LocalDateTime expiredAt, Long orgId) {
        VerifyCodeDO verifyCodeDO = new VerifyCodeDO();

        verifyCodeDO.setMobile(mobile);
        verifyCodeDO.setCode(code);
        verifyCodeDO.setExpiredAt(expiredAt);
        verifyCodeDO.setOrgId(orgId);
        verifyCodeDO.setCreatedAt(LocalDateTime.now());

        verifyCodeDAO.insertSelective(verifyCodeDO);
    }

    @Override
    public boolean verify(String mobile, String code) {
        VerifyCodeDO verifyCodeDO = verifyCodeDAO.selectByMobile(mobile);

        if (null == verifyCodeDO) {
            return false;
        }

        if ( !code.equals(verifyCodeDO.getCode()) ) {
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        if (verifyCodeDO.getExpiredAt().isBefore(now)) {
            return false;
        }

        deactivate(verifyCodeDO.getId(), now);
        return true;
    }

    private void deactivate(Long id, LocalDateTime expiredAt) {
        verifyCodeDAO.updateExpiredAtById(id, expiredAt);
    }


}
