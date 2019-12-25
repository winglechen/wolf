package study.daydayup.wolf.business.trade.buy.biz.common;

import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayNotifyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayNotifyResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PreviewResponse;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.flow
 *
 * @author Wingle
 * @since 2019/10/5 10:54 AM
 **/
public interface TradeFlow {
    void init();
    List<TradeNode> buildConfirmFlow();
    List<TradeNode> buildPreviewFlow();
    List<TradeNode> buildPayFlow();
    List<TradeNode> buildPayNotifyFlow();

    PreviewResponse preview(BuyRequest request);
    ConfirmResponse confirm(BuyRequest request);
    PayResponse pay(PayRequest request);
    PayNotifyResponse payNotify(PayNotifyRequest request);
}
