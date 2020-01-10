package study.daydayup.wolf.business.trade.tm.biz.engine.order;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.tm.TradeRequest;
import study.daydayup.wolf.business.trade.tm.biz.engine.QueryEngine;
import study.daydayup.wolf.business.trade.tm.biz.engine.QueryFilter;
import study.daydayup.wolf.business.trade.tm.biz.engine.core.QueryResponse;

/**
 * study.daydayup.wolf.business.trade.tm.biz.engine.contract
 *
 * @author Wingle
 * @since 2020/1/10 1:37 下午
 **/
@Component
public class ByTradeNo implements QueryFilter {
    @Override
    public <T extends TradeRequest> void doFilter(T request, QueryResponse response, QueryEngine engine) {

    }
}
