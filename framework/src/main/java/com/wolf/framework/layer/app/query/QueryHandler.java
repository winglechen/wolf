package com.wolf.framework.layer.app.query;

import com.wolf.common.lang.contract.Context;
import com.wolf.framework.layer.api.Request;
import com.wolf.framework.layer.api.Response;
import com.wolf.framework.layer.domain.Filter;

/**
 * QueryHandler
 */
public interface QueryHandler<T extends Response, R extends Request, C extends Context> extends Filter {
    T handle(R request, C context);

    default T handle(R request) {
        return handle(request, null);
    }
}
