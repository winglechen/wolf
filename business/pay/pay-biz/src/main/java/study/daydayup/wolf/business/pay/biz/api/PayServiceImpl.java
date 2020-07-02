package study.daydayup.wolf.business.pay.biz.api;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentChannelEnum;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.InvalidPayRequestException;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyResponse;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutResponse;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeRequest;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeResponse;
import study.daydayup.wolf.business.pay.api.service.PayService;
import study.daydayup.wolf.business.pay.api.service.PayoutService;
import study.daydayup.wolf.business.pay.biz.domain.factory.PayServiceFactory;
import study.daydayup.wolf.business.pay.biz.domain.service.PgDomainService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.api
 *
 * @author Wingle
 * @since 2020/4/26 6:25 下午
 **/
@RpcService
public class PayServiceImpl implements PayService, PayoutService {
    @Resource
    private PgDomainService pgDomainService;

    @Override
    public Result<PaymentCreateResponse> create(@Validated PaymentCreateRequest request) {
        return pgDomainService.create(request);
    }

    @Override
    public Result<PayVerifyResponse> verify(PayVerifyRequest request) {
        return pgDomainService.verify(request);
    }

    @Override
    public Result<SubscribeResponse> subscribe(@Validated SubscribeRequest request) {
        return pgDomainService.subscribe(request);
    }

    @Override
    public Result<PayoutResponse> payout(PayoutRequest request) {
        return null;
    }

}
