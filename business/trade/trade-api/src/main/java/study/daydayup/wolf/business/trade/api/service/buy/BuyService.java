package study.daydayup.wolf.business.trade.api.service.buy;

import study.daydayup.wolf.business.trade.api.dto.buy.request.ConfirmRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PrepareRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PrepareResponse;

/**
 * study.daydayup.wolf.business.trade.api.service.buy
 *
 * @author Wingle
 * @since 2019/10/5 2:28 PM
 **/
public interface BuyService {
    PrepareResponse prepare(PrepareRequest request);
    ConfirmResponse confirm(ConfirmRequest request);
    PayResponse pay(PayRequest request);
}
