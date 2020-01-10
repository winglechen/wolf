package study.daydayup.wolf.business.trade.tm.biz.engine.core;

import study.daydayup.wolf.business.trade.api.dto.tm.TradeRequest;
import study.daydayup.wolf.business.trade.tm.biz.engine.QueryEngine;
import study.daydayup.wolf.business.trade.tm.biz.engine.QueryFilter;

import java.util.Stack;

/**
 * study.daydayup.wolf.business.trade.tm.biz.engine
 *
 * @author Wingle
 * @since 2020/1/10 1:17 下午
 **/
public abstract class AbstractEngine<T extends TradeRequest> implements QueryEngine {
    protected Stack<QueryFilter> filters;
    protected QueryContext context;

    protected QueryResponse response;
    protected T  request;

    protected void init(T request) {
        validRequest(request);
        this.request = request;
        response = new QueryResponse();

        context = new QueryContext();
        filters = new Stack<>();
    }

    public void addFilter(QueryFilter filter) {
        if (filter == null) {
            return;
        }

        filters.push(filter);
    }

    protected void doFilter() {
        if (filters.empty()) {
            return;
        }

        QueryFilter filter = filters.pop();
        filter.doFilter(request, response, this);
    }

    protected void validRequest(T request) {
        if (request == null) {
            throw new IllegalArgumentException("Contract Request can't be null");
        }

        request.valid();
    }

}
