package study.daydayup.wolf.business.pay.api.service.india;

import study.daydayup.wolf.business.pay.api.service.PayService;
import study.daydayup.wolf.business.pay.api.service.PayoutService;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.pay.api.service.india
 *
 * @author Wingle
 * @since 2020/2/26 3:16 下午
 **/
public interface RazorpayService extends PayService, PayoutService {

    Result<Integer> subscribe(String eventId, String signature, String data);
}
