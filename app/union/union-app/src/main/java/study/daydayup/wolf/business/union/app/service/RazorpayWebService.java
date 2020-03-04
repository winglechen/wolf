package study.daydayup.wolf.business.union.app.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.service.india.RazorpayService;
import study.daydayup.wolf.business.trade.api.service.buy.LoanService;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.union.app.service
 *
 * @author Wingle
 * @since 2020/2/27 7:42 下午
 **/
@Component
public class RazorpayWebService {
    @Reference
    private LoanService loanService;
//    @Reference
    private RazorpayService razorpayService;

    public Result<PaymentCreateResponse> create(PaymentCreateRequest request) {
        //获取合同信息

        //确认分期情况

        //查找分期还款订单
            //存在则查询还款订单
            //不存在则生成还款订单

        //格式化支付参数

        //支付请求

        return null;
    }
}
