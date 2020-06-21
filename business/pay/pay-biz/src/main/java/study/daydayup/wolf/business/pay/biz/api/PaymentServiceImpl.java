package study.daydayup.wolf.business.pay.biz.api;

import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentQuery;
import study.daydayup.wolf.business.pay.api.service.PaymentService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

/**
 * study.daydayup.wolf.business.pay.biz.api
 *
 * @author Wingle
 * @since 2020/6/21 5:43 下午
 **/
@RpcService
public class PaymentServiceImpl implements PaymentService {
    @Override
    public Result<Page<Payment>> query(PaymentQuery query, PageRequest pageRequest) {
        return null;
    }
}
