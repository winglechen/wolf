package study.daydayup.wolf.business.trade.tm.biz.engine;

import study.daydayup.wolf.business.trade.api.dto.tm.TradeRequest;
import study.daydayup.wolf.framework.layer.domain.Filter;

/**
 * study.daydayup.wolf.business.trade.tm.biz.engine
 *
 * @author Wingle
 * @since 2020/1/10 1:13 下午
 **/
public interface QueryFilter extends Filter {
    <T extends TradeRequest> void doFilter(T request, QueryResponse response, QueryEngine engine );
}
