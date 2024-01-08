package study.daydayup.wolf.framework.layer.app.query;

import study.daydayup.wolf.common.lang.contract.Context;
import study.daydayup.wolf.framework.layer.api.Request;
import study.daydayup.wolf.framework.layer.api.Response;
import study.daydayup.wolf.framework.layer.domain.Filter;

/**
 * QueryHandler
 */
public interface QueryHandler<T extends Response, R extends Request, C extends Context> extends Filter {
    T handle(R request, C context);

    default T handle(R request) {
        return handle(request, null);
    }
}
