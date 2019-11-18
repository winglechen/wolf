package study.daydayup.wolf.demo.account.biz.service.impl;

import study.daydayup.wolf.demo.account.api.dto.request.VerifyCodeRequest;
import study.daydayup.wolf.demo.account.api.dto.response.VerifyCodeResponse;
import study.daydayup.wolf.demo.account.api.exception.VerifyCodeSendErrorException;
import study.daydayup.wolf.demo.account.api.service.VerifyCodeService;
import study.daydayup.wolf.demo.account.biz.authorization.entity.VerifyCode;
import study.daydayup.wolf.demo.account.biz.dal.dao.VerifyCodeDAO;
import study.daydayup.wolf.demo.account.biz.dal.dataobject.VerifyCodeDO;
import lombok.extern.slf4j.Slf4j;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

@RpcService
@Slf4j
public class VerifyCodeServiceImpl implements VerifyCodeService {

    private static Long interval = 60 * 1000L;

    @Resource
    private VerifyCodeDAO verifyCodeDAO;

    @Override
    public VerifyCodeResponse send(@Valid VerifyCodeRequest verifyCodeRequest) {
        VerifyCodeDO verifyCodeDO = verifyCodeDAO.getByMobile(verifyCodeRequest.getMobile());

        long now = (new Date()).getTime();
        long duration = now - verifyCodeDO.getCreatedAt().getTime();
        if (verifyCodeDO != null  &&  duration < interval ) {
            throw new VerifyCodeSendErrorException("验证码再次发送间隔需要60秒");
        }
        //todo send sms service

        VerifyCode verifyCode = new VerifyCode(verifyCodeRequest.getMobile());
        verifyCodeDO = new VerifyCodeDO();
        verifyCodeDO.setMobile(verifyCode.getMobile());
        verifyCodeDO.setCode(verifyCode.getCode());
        verifyCodeDO.setExpiredAt(verifyCode.getExpiredVO().getExpiredAt());
        verifyCodeDO.setIsDelete(0);
        verifyCodeDO.setCreatedAt(verifyCode.getCreateAt());
        verifyCodeDO.setUpdatedAt(new Date());
        verifyCodeDAO.insert(verifyCodeDO);

        VerifyCodeResponse verifyCodeResponse = new VerifyCodeResponse();
        verifyCodeResponse.setMobile(verifyCodeRequest.getMobile());
        verifyCodeResponse.setNextSendSeconds(60);
        return verifyCodeResponse;
    }
}
