package com.wolf.framework.layer.domain.uid;

import com.wolf.common.lang.exception.SystemException;
import java.util.concurrent.ConcurrentHashMap;

public class IDContainer {
    private static final IDContainer INSTANCE = new IDContainer();
    private final ConcurrentHashMap<String, Integer> idMap;

    public static IDContainer singleton() {
        return INSTANCE;
    }

    private IDContainer() {
        this.idMap = new ConcurrentHashMap<>();
    }

    public void initId(String name, int id) {
        this.idMap.put(name, id);
    }

    public int getAndIncrease(String name) {
        if (!this.idMap.containsKey(name)) {
            throw new SystemException("can't find wolfID name: " + name);
        }

        return 0;
    }
}
