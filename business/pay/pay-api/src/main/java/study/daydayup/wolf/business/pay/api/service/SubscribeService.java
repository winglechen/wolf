package study.daydayup.wolf.business.pay.api.service;

import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeRequest;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeResponse;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.pay.api.service
 *
 * @author Wingle
 * @since 2020/4/29 11:06 上午
 **/
public interface SubscribeService {
    Result<SubscribeResponse> subscribe(SubscribeRequest request);
}
