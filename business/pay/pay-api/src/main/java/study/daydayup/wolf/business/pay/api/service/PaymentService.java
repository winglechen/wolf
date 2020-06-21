package study.daydayup.wolf.business.pay.api.service;

import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.dto.base.manage.PaymentQuery;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

/**
 * study.daydayup.wolf.business.pay.api.service
 *
 * @author Wingle
 * @since 2020/6/21 5:36 下午
 **/
public interface PaymentService extends Service {
    Result<Page<Payment>> query(PaymentQuery query, PageRequest pageRequest);
}
