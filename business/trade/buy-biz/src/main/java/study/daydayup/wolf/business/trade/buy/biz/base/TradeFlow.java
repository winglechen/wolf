package study.daydayup.wolf.business.trade.buy.biz.base;

import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayResultRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.BuyResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResultResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResponse;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.flow
 * TODO refactor
 * @author Wingle
 * @since 2019/10/5 10:54 AM
 **/
public interface TradeFlow {
    void init();
    List<TradeNode> buildConfirmFlow();
    List<TradeNode> buildPreviewFlow();
    List<TradeNode> buildPayFlow();
    List<TradeNode> buildPayNotifyFlow();

    BuyResponse preview(BuyRequest request);
    BuyResponse confirm(BuyRequest request);
    PayResponse pay(PayRequest request);
    PayResultResponse payNotify(PayResultRequest request);
}
