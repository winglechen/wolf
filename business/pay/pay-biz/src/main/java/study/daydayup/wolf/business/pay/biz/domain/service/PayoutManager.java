package study.daydayup.wolf.business.pay.biz.domain.service;

import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutResponse;
import study.daydayup.wolf.framework.layer.domain.Service;


/**
 * study.daydayup.wolf.business.pay.biz.domain.service
 *
 * @author Wingle
 * @since 2020/3/22 11:21 下午
 **/
public interface PayoutManager extends Service {
    PayoutResponse payout(PayoutRequest request);
}
