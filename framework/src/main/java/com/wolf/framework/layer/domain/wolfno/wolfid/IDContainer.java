package com.wolf.framework.layer.domain.wolfno.wolfid;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

public class IDContainer {
    /**
     *
     */
    public final static int DEFAULT_STEP = 1000;

    /**
     *
     */
    public final static double DEFAULT_RATE = 0.6;

    private static final IDContainer INSTANCE = new IDContainer();

    private final ConcurrentHashMap<String, WolfID> idMap;
    private final ConcurrentHashMap<String, WolfID> idStandby;



    public static IDContainer singleton() {
        return INSTANCE;
    }

    private IDContainer() {
        this.idMap = new ConcurrentHashMap<>();
        this.idStandby = new ConcurrentHashMap<>();
    }

    public boolean addWolfID(WolfID wolfID) {
        WolfID result = this.idMap.putIfAbsent(wolfID.getName(), wolfID);

        return result == null;
    }

    public boolean addStandbyID(WolfID wolfID) {
        WolfID result = this.idStandby.putIfAbsent(wolfID.getName(), wolfID);

        return result == null;
    }

    public Integer getAndIncrease(String name, LocalDateTime ts) {
        if (!this.idMap.containsKey(name)) {
            return null;
        }

        WolfID wolfID = this.idMap.get(name);
        int id = wolfID.getCurrentID().getAndIncrement();
        if (id < wolfID.getMaxID()) {
            return id;
        }

        if (wolfID.isHasStandBy() && this.idStandby.containsKey(name)) {
            this.idMap.put(name, this.idStandby.get(name));
            this.idStandby.remove(name);
        } else {
            this.idMap.remove(name);
        }

        return null;
    }
}
