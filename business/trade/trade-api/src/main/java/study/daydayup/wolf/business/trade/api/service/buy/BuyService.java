package study.daydayup.wolf.business.trade.api.service.buy;

import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayNotifyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayNotifyResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PreviewResponse;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.trade.api.service.buy
 *
 * @author Wingle
 * @since 2019/10/5 2:28 PM
 **/
public interface BuyService {
    Result<PreviewResponse> preview(BuyRequest request);
    Result<ConfirmResponse> confirm(BuyRequest request);
    Result<PayResponse> pay(PayRequest request);
    Result<PayNotifyResponse> payNotify(PayNotifyRequest request);
}
