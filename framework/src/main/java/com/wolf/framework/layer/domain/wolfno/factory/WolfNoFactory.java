package com.wolf.framework.layer.domain.wolfno.factory;

import com.wolf.common.ds.map.LockMap;
import com.wolf.framework.layer.domain.wolfno.model.WolfNoContext;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WolfNoFactory {
    private static final int POOL_SIZE = 1;
    private static final int POOL_MAX_SIZE = 3;
    private static final int KEEP_ALIVE_TIME = 60;

    private final WolfNoContext context;
    private final LockMap lockMap;
    private final Executor executor;

    public WolfNoFactory(WolfNoContext context) {
        this.context = context;
        this.lockMap = new LockMap();

        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        this.executor = new ThreadPoolExecutor(POOL_SIZE, POOL_MAX_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, queue);
    }

    public String create() {
        return "";
    }
}
