package com.onedot.win.framework.layer.app.query;

import com.onedot.win.common.lang.contract.Context;
import com.onedot.win.framework.layer.api.Request;
import com.onedot.win.framework.layer.api.Response;
import com.onedot.win.framework.layer.domain.Filter;

/**
 * QueryHandler
 */
public interface QueryHandler<T extends Response, R extends Request, C extends Context> extends Filter {
    T handle(R request, C context);

    default T handle(R request) {
        return handle(request, null);
    }
}
