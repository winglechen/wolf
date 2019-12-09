package study.daydayup.wolf.business.account.biz.service.impl;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.account.biz.dal.dao.VerifyCodeDAO;
import study.daydayup.wolf.business.account.biz.dal.dataobject.VerifyCodeDO;
import study.daydayup.wolf.business.account.biz.service.VerifyCodeService;

import javax.annotation.Resource;
import java.util.Date;

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
    public void send(String mobile, String code, Date expiredAt) {
        VerifyCodeDO verifyCodeDO = new VerifyCodeDO();

        verifyCodeDO.setMobile(mobile);
        verifyCodeDO.setCode(code);
        verifyCodeDO.setExpiredAt(expiredAt);
        verifyCodeDO.setCreatedAt(new Date());

        verifyCodeDAO.insertSelective(verifyCodeDO);
    }

    @Override
    public boolean verify(String mobile, String code) {
        VerifyCodeDO verifyCodeDO = verifyCodeDAO.selectByMobile(mobile);

        if (null == verifyCodeDO) {
            System.out.println("no verify code");
            return false;
        }

        if ( !code.equals(verifyCodeDO.getCode()) ) {
            System.out.println("different code");
            return false;
        }

        Date now = new Date();
        if (verifyCodeDO.getExpiredAt().before(now)) {
            System.out.println("code expired");
            return false;
        }
        System.out.println("correct code");

        return true;
    }
}
