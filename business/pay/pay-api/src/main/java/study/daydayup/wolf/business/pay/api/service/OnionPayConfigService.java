package study.daydayup.wolf.business.pay.api.service;

import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;


/**
 * study.daydayup.wolf.business.pay.api.service
 *
 * @author Wingle
 * @since 2020/7/6 9:12 下午
 **/
public interface OnionPayConfigService extends Service {
    Result<String> findReturnUrl();
    Result<String> findReturnUrl(int paymentChannel);
}
