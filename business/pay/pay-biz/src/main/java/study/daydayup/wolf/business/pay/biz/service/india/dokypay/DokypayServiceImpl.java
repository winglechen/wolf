package study.daydayup.wolf.business.pay.biz.service.india.dokypay;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.InvalidPayRequestException;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyResponse;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutResponse;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeRequest;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeResponse;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.rpc.Result;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.api.india
 *
 * @author Wingle
 * @since 2020/4/26 6:42 下午
 **/
@Component
public class DokypayServiceImpl implements DokypayService {
    @Resource
    private DokypayCreator dokypayCreator;
    @Resource
    private DokypaySubscriber dokypaySubscriber;

    @Override
    public Result<PaymentCreateResponse> create(PaymentCreateRequest request) {
        PaymentCreateResponse response = dokypayCreator.create(request);
        return Result.ok(response);
    }

    @Override
    public Result<PayVerifyResponse> verify(PayVerifyRequest request) {
        return null;
    }

    @Override
    public Result<PayoutResponse> payout(PayoutRequest request) {
        return null;
    }

    @Override
    public Result<SubscribeResponse> subscribe(@Validated SubscribeRequest request) {
        if (StringUtil.isBlank(request.getData())) {
            throw  new InvalidPayRequestException("dokypay data can't be blank");
        }
        int code = dokypaySubscriber.subscribe(request.getData());
        SubscribeResponse response = SubscribeResponse.builder()
                .code(code)
                .build();
        return Result.ok(response);
    }
}
