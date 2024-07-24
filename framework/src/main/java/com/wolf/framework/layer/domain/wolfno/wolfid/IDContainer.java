package com.wolf.framework.layer.domain.wolfno.wolfid;

import com.wolf.common.lang.exception.SystemException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class IDContainer {
    private static final IDContainer INSTANCE = new IDContainer();
    private final ConcurrentHashMap<String, AtomicInteger> idMap;
    private final ConcurrentHashMap<String, AtomicInteger> idBackup;

    public static IDContainer singleton() {
        return INSTANCE;
    }

    private IDContainer() {
        this.idMap = new ConcurrentHashMap<>();
        this.idBackup = new ConcurrentHashMap<>();
    }

    public void initId(String name, int id) {
        this.idMap.put(name, new AtomicInteger(0));
    }

    public int getAndIncrease(String name) {
        if (!this.idMap.containsKey(name)) {
            throw new SystemException("can't find wolfID name: " + name);
        }

        return 0;
    }
}
