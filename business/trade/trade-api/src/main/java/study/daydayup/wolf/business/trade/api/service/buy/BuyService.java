package study.daydayup.wolf.business.trade.api.service.buy;

import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayResultRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.BuyResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResultResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResponse;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.trade.api.service.buy
 *
 * @author Wingle
 * @since 2019/10/5 2:28 PM
 **/
public interface BuyService {
    /**
     * 订单预览
     * @param request 同真实下单参数
     * @return 预览的订单信息
     */
    Result<BuyResponse> preview(BuyRequest request);

    /**
     * 用户下单接口
     * @param request 下单参数
     * @return 下单完成状态
     */
    Result<BuyResponse> confirm(BuyRequest request);

    /**
     * 用户端发起的支付请求
     * @param request 支付请求参数
     * @return 整合支付系统返回的支付参数信息、统一返回用户端
     */
    Result<PayResponse> pay(PayRequest request);

    /**
     * 异步支付结果拉取, 前端可以轮询该接口
     * @param request 支付结果参数
     * @return 同步支付结果
     */
    Result<PayResultResponse> payResult(PayResultRequest request);
}
