package study.daydayup.wolf.business.trade.buy.biz.api;

import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayNotifyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayNotifyResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PreviewResponse;
import study.daydayup.wolf.business.trade.api.service.buy.BuyService;
import study.daydayup.wolf.business.trade.buy.biz.base.TradeFlowService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.buy.service.impl
 *
 * @author Wingle
 * @since 2019/10/9 1:59 下午
 **/
@RpcService(protocol = "dubbo")
public class BuyServiceImpl implements BuyService {
    @Resource
    private TradeFlowService flowService;

    @Override
    public Result<PreviewResponse> preview(@Validated BuyRequest request) {
        PreviewResponse response =  flowService.preview(request);
        return Result.ok(response);
    }

    @Override
    public Result<ConfirmResponse> confirm(@Validated BuyRequest request) {
        ConfirmResponse response = flowService.confirm(request);
        return Result.ok(response);
    }

    @Override
    public Result<PayResponse> pay(@Validated PayRequest request) {
        PayResponse response = flowService.pay(request);
        return Result.ok(response);
    }

    @Override
    public Result<PayNotifyResponse> payNotify(@Validated PayNotifyRequest request) {
        PayNotifyResponse response = flowService.payNotify(request);
        return Result.ok(response);
    }
}
