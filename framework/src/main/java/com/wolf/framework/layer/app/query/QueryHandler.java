package com.wolf.framework.layer.app.query;

import com.wolf.common.contract.container.Context;
import com.wolf.framework.layer.api.dto.Request;
import com.wolf.framework.layer.api.dto.Response;

/**
 * QueryHandler
 */
public interface QueryHandler<T extends Response, R extends Request, C extends Context> {
    T handle(R request, C context);

    default T handle(R request) {
        return handle(request, null);
    }
}
