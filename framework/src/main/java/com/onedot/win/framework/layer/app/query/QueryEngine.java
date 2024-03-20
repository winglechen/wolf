package com.onedot.win.framework.layer.app.query;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import com.onedot.win.common.lang.contract.Context;
import com.onedot.win.common.lang.exception.lang.IllegalArgumentException;
import com.onedot.win.common.util.collection.CollectionUtil;
import com.onedot.win.common.util.collection.MapUtil;
import com.onedot.win.common.util.lang.BeanUtil;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.framework.layer.api.Request;
import com.onedot.win.framework.layer.api.Response;
import com.onedot.win.framework.rpc.page.PageRequest;
import com.onedot.win.framework.util.ContextUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Slf4j
public class QueryEngine<T extends Response, R extends Request, C extends Context> implements QueryChain<T, R, C> {
    /**
     * empty handler
     * <p>
     * if it is null and query isEmpty
     * execute defaultHandler
     */
    private QueryHandler<T, R, C> emptyHandler;

    /**
     * default handler
     * <p>
     * if not set return null
     */
    private QueryHandler<T, R, C> defaultHandler;

    /**
     * handler chain
     */
    private final List<QueryHandler<T, R, C>> chain;

    /**
     * list of key set of chain
     * <p>
     * share same index with handler chain
     */
    private final List<HashSet<String>> keyChain;

    /**
     * exactHandlerMap
     * <p>
     * existing exactHandler will have the value true
     */
    private final Map<QueryHandler<T, R, C>, Boolean> exactHandlerMap;

    public QueryEngine() {
        emptyHandler = null;
        chain = new ArrayList<>();
        keyChain = new ArrayList<>();
        exactHandlerMap = new HashMap<>();
    }

    @Override
    public <Y extends QueryHandler<T, R, C>> QueryChain<T, R, C> addEmptyHandler(@NonNull Class<Y> cls) {
        QueryHandler<T, R, C> filter = ContextUtil.getBean(cls);
        if (this.emptyHandler != null) {
            throw new IllegalArgumentException("already have empty filter");
        }
        this.emptyHandler = filter;
        return this;
    }

    @Override
    public <Y extends QueryHandler<T, R, C>> QueryChain<T, R, C> addDefaultHandler(@NonNull Class<Y> cls) {
        QueryHandler<T, R, C> filter = ContextUtil.getBean(cls);

        if (this.defaultHandler != null) {
            throw new IllegalArgumentException("already have default filter");
        }
        this.defaultHandler = filter;
        return this;
    }

    @Override
    public <Y extends QueryHandler<T, R, C>> QueryChain<T, R, C> addHandler(@NonNull Class<Y> cls, String... keys) {
        QueryHandler<T, R, C> filter = ContextUtil.getBean(cls);

        List<String> keysList = Arrays.asList(keys);
        if (keysList.size() == 0) {
            throw new IllegalArgumentException("QueryHandler must have one match key");
        }

        chain.add(filter);
        HashSet<String> hashSet = new HashSet<>(keysList);
        keyChain.add(hashSet);
        exactHandlerMap.put(filter, false);

        return this;
    }

    @Override
    public <Y extends CustomQueryHandler<T, R, C>> QueryChain<T, R, C> addCustomHandler(@NonNull Class<Y> cls) {
        QueryHandler<T, R, C> filter = ContextUtil.getBean(cls);

        chain.add(filter);
        keyChain.add(new HashSet<>());
        exactHandlerMap.put(filter, false);

        return this;
    }

    @Override
    public <Y extends QueryHandler<T, R, C>> QueryChain<T, R, C> addExactHandler(@NonNull Class<Y> cls, String... keys) {
        QueryHandler<T, R, C> filter = ContextUtil.getBean(cls);

        chain.add(filter);
        HashSet<String> hashSet = new HashSet<>(Arrays.asList(keys));
        keyChain.add(hashSet);
        exactHandlerMap.put(filter, true);

        return this;
    }

    @Override
    public T execute(R request, C context) {
        Map<String, Object> requestMap = prepareRequest(request);

        if (null != emptyHandler && MapUtil.isEmpty(requestMap)) {
            log.info("Query Engine: Empty Handler: {} Request: {}", emptyHandler, request);
            return emptyHandler.handle(request, context);
        }

        for (int i = 0; i < chain.size(); i++) {
            QueryHandler<T, R, C> filter = chain.get(i);
            HashSet<String> keySet = keyChain.get(i);

            if (filter instanceof CustomQueryHandler) {
                T response = filter.handle(request, context);
                // if custom query handler's response is null, continue to run next
                if (response != null) {
                    log.info("Query Engine: Custom Handler: {} Request: {}, Reponse: {}", filter, request, response);
                    return response;
                }
                continue;
            }

            if (isMatched(keySet, requestMap, filter)) {
                T response = filter.handle(request, context);
                log.info("Query Engine: {} Request: {} Response: {}", filter, request, response);
                return response;
            }
        }

        if (null != defaultHandler) {
            log.info("Query Engine: Default Handler: {} Request: {}", defaultHandler, request);
            return defaultHandler.handle(request, context);
        }

        throw new QueryHandlerNotFoundException();
    }

    private Map<String, Object> prepareRequest(R request) {
        Map<String, Object> requestMap = MapUtil.filterBlank(BeanUtil.toMap(request));
        // remove pageRequest property name
        ArrayList<String> ignoreList = BeanUtil.getPropertyNames(PageRequest.class);
        ignoreList.forEach(requestMap::remove);
        log.info("Query Engine: prepareRequest: Request: {} RequestMap: {}, IgnoreList: {}", request, requestMap, ignoreList);

        return requestMap;
    }

    private boolean isMatched(@NonNull HashSet<String> set, @NonNull Map<String, Object> requestMap, QueryHandler<T, R, C> filter) {
        // exact match
        if (exactHandlerMap.get(filter)) {
            return set.equals(requestMap.keySet());
        }

        for (String requestKey : set) {
            if (!requestMap.containsKey(requestKey)) {
                return false;
            }

            Object val = requestMap.get(requestKey);
            if (!isValidValue(val)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidValue(Object v) {
        if (v instanceof String) {
            return StringUtil.notBlank(v);
        }

        if (v instanceof Collection) {
            return !CollectionUtil.isEmpty((Collection<?>) v);
        }

        if (v instanceof Map) {
            return !MapUtil.isEmpty((Map<?, ?>) v);
        }

        return null != v;
    }
}
