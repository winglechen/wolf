package com.onedot.win.framework.layer.app.query;

import com.onedot.win.common.lang.contract.Context;
import com.onedot.win.framework.layer.api.Request;
import com.onedot.win.framework.layer.api.Response;

/**
 * QueryHandler
 */
public interface CustomQueryHandler<T extends Response, R extends Request, C extends Context> extends QueryHandler<T, R, C> {
}
