package study.daydayup.wolf.framework.layer.app.query;

import study.daydayup.wolf.common.lang.contract.Context;
import study.daydayup.wolf.framework.layer.api.Request;
import study.daydayup.wolf.framework.layer.api.Response;

/**
 * QueryHandler
 */
public interface CustomQueryHandler<T extends Response, R extends Request, C extends Context> extends QueryHandler<T, R, C> {
}
