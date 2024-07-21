package com.wolf.framework.layer.app.query;

import com.wolf.common.lang.contract.Context;
import com.wolf.framework.layer.api.Request;
import com.wolf.framework.layer.api.Response;

/**
 * QueryHandler
 */
public interface CustomQueryHandler<T extends Response, R extends Request, C extends Context> extends QueryHandler<T, R, C> {
}
