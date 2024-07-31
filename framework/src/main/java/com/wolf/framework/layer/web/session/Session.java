package com.wolf.framework.layer.web.session;

import com.wolf.common.ds.map.ObjectMap;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

public class Session {
    private static final String DEFAULT_NAMESPACE = "default";

    private Cookie cookie;
    private String sessionId;

    private Map<String, ObjectMap> sessionData;
    private Set<String> changedNamespaces;

    private final RedisTemplate<String, ObjectMap> redisTemplate;
    private final HashOperations<String, String, ObjectMap> hashOperations;

    public Session(RedisTemplate<String, ObjectMap> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void start(HttpServletRequest request, HttpServletResponse response) {
        this.cookie = new Cookie(request, response);

        this.getOrCreateSessionId();
        this.loadSessionData();

        this.changedNamespaces = new HashSet<>();
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(DEFAULT_NAMESPACE, key, clazz);
    }

    public <T> T get(String namespace, String key, Class<T> clazz) {
        ObjectMap data = sessionData.get(namespace);
        if (data == null) {
            return null;
        }

        return data.getObject(key, clazz);
    }

    public ObjectMap getNamespaces(String namespace) {
        return this.sessionData.get(namespace);
    }

    public Session setNamespace(String namespace, ObjectMap map) {
        this.sessionData.put(namespace, map);
        return this;
    }

    public Session set(String key, Object value) {
        return set(DEFAULT_NAMESPACE, key, value);
    }

    public Session set(String namespace, String key, Object value) {
        ObjectMap data = sessionData.get(namespace);
        if (data == null) {
            data = new ObjectMap();
            sessionData.put(namespace, data);
        }

        data.put(key, value);
        return this;
    }

    public boolean save() {
        return true;
    }

    private void getOrCreateSessionId() {

    }

    private void loadSessionData() {
        this.sessionData = new HashMap<>();
    }

}
