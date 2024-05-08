package com.wolf.framework.layer.app.query;

import lombok.NonNull;
import com.wolf.common.lang.contract.Context;
import com.wolf.framework.layer.api.Request;
import com.wolf.framework.layer.api.Response;

public interface QueryChain<T extends Response, R extends Request, C extends Context> {
    <Y extends QueryHandler<T, R, C>> QueryChain<T, R, C> addEmptyHandler(@NonNull Class<Y> cls);

    <Y extends QueryHandler<T, R, C>> QueryChain<T, R, C> addHandler(@NonNull Class<Y> cls, String... keys);

    <Y extends CustomQueryHandler<T, R, C>> QueryChain<T, R, C> addCustomHandler(@NonNull Class<Y> cls);

    <Y extends QueryHandler<T, R, C>> QueryChain<T, R, C> addExactHandler(@NonNull Class<Y> cls, String... keys);

    <Y extends QueryHandler<T, R, C>> QueryChain<T, R, C> addDefaultHandler(@NonNull Class<Y> cls);

    T execute(R request, C context);
}
