package study.daydayup.wolf.business.pay.api.service.india;

import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.pay.api.service.india
 *
 * @author Wingle
 * @since 2020/2/26 3:16 下午
 **/
public interface RazorpayService {
    Result<String> notify(String data);
    Result<Boolean> checkout(String data);
}
