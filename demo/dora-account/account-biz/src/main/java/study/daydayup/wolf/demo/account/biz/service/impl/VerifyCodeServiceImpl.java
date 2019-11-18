package study.daydayup.wolf.demo.account.biz.service.impl;

import study.daydayup.wolf.demo.account.api.dto.request.VerifyCodeSendRequest;
import study.daydayup.wolf.demo.account.api.dto.response.VerifyCodeSendResponse;
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
    public VerifyCodeSendResponse send(@Valid VerifyCodeSendRequest verifyCodeSendRequest) {
        VerifyCodeDO verifyCodeDO = verifyCodeDAO.getByMobile(verifyCodeSendRequest.getMobile());
        if (verifyCodeDO != null && (((new Date()).getTime() - verifyCodeDO.getCreatedAt().getTime()) < interval)) {
            throw new VerifyCodeSendErrorException("验证码再次发送间隔需要60秒");
        }
        //todo send sms service

        VerifyCode verifyCode = new VerifyCode(verifyCodeSendRequest.getMobile());
        verifyCodeDO = new VerifyCodeDO();
        verifyCodeDO.setMobile(verifyCode.getMobile());
        verifyCodeDO.setCode(verifyCode.getCode());
        verifyCodeDO.setExpiredAt(verifyCode.getExpiredVO().getExpiredAt());
        verifyCodeDO.setIsDelete(0);
        verifyCodeDO.setCreatedAt(verifyCode.getCreateAt());
        verifyCodeDO.setUpdatedAt(new Date());
        verifyCodeDAO.insert(verifyCodeDO);

        VerifyCodeSendResponse verifyCodeSendResponse = new VerifyCodeSendResponse();
        verifyCodeSendResponse.setMobile(verifyCodeSendRequest.getMobile());
        verifyCodeSendResponse.setNextSendSeconds(60);
        return verifyCodeSendResponse;
    }
}
