package com.wolf.wolfno.wolfid;

import com.wolf.common.ds.map.LockMap;
import com.wolf.common.lang.exception.SystemException;
import com.wolf.wolfno.config.WolfNoConfig;
import com.wolf.wolfno.model.WolfNoContext;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.jdbc.core.JdbcTemplate;

public class WolfIDContainer {
    private static final int POOL_SIZE = 1;
    private static final int POOL_MAX_SIZE = 3;
    private static final int KEEP_ALIVE_TIME = 60;

    public final static int DEFAULT_STEP = 1000;
    public final static double DEFAULT_RATE = 0.6;

    private static final WolfIDContainer INSTANCE = new WolfIDContainer();

    private final ConcurrentHashMap<String, WolfID> idMap;
    private final ConcurrentHashMap<String, WolfID> idStandby;
    private final LockMap lockMap;

    private WolfIDFetcher fetcher;
    private ThreadPoolExecutor executor;

    public static WolfIDContainer singleton() {
        return INSTANCE;
    }

    private WolfIDContainer() {
        this.idMap = new ConcurrentHashMap<>();
        this.idStandby = new ConcurrentHashMap<>();
        this.lockMap = new LockMap();
    }

    public void init(WolfNoConfig config, JdbcTemplate jdbcTemplate) {
        this.fetcher = new WolfIDFetcher(config, jdbcTemplate);

        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        this.executor = new ThreadPoolExecutor(
            POOL_SIZE, POOL_MAX_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, queue);
    }

    public boolean addWolfID(WolfID wolfID) {
        WolfID result = this.idMap.putIfAbsent(wolfID.getName(), wolfID);

        return result == null;
    }

    public boolean addStandbyID(WolfID wolfID) {
        WolfID result = this.idStandby.putIfAbsent(wolfID.getName(), wolfID);

        return result == null;
    }

    public String getWolfID(WolfNoContext context) {
        return "";
    }

    public WolfID getAndIncrease(WolfNoContext context) {
        String name = context.getName();
        WolfID wolfID = this.idMap.get(name);
        if (null == wolfID) {
            return fetch(context);
        }

        int id = wolfID.getCurrentID().getAndIncrement();
        if (id < wolfID.getMaxID()) {
            this.tryAsyncFetch(context);
            return wolfID;
        }

        this.idMap.remove(name);
        WolfID standby = this.idStandby.get(name);

        if (!wolfID.isHasStandBy() || null == standby) {
            return fetch(context);
        }

        this.idMap.put(name, standby);
        this.idStandby.remove(name);
        return standby;
    }

    private WolfID fetch(WolfNoContext context) {
        String name = context.getName();
        boolean status = this.lockMap.tryLock(name);
        if (!status) {
            throw new SystemException("failed to lock id " + name);
        }

        IDResult result = this.fetcher.getID(context, DEFAULT_STEP);

        this.lockMap.unlock(name);
        return null;
    }

    private void tryAsyncFetch(WolfNoContext context) {


    }

    private void asyncFetch(WolfNoContext context) {
        String name = context.getName();
        boolean status = this.lockMap.tryLock(name);
        if (!status) {
            return;
        }

        WolfIDContainer that = this;
        this.executor.submit(() -> {
            IDResult result = that.fetcher.getID(context, DEFAULT_STEP);

            that.lockMap.unlock(name);
        });
    }
}
