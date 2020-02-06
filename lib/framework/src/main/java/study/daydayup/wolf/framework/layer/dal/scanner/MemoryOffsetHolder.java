package study.daydayup.wolf.framework.layer.dal.scanner;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * study.daydayup.wolf.framework.layer.dal.scanner
 *
 * @author Wingle
 * @since 2020/2/5 12:07 下午
 **/
public class MemoryOffsetHolder implements OffsetHolder {
    private Map<String, Long> offsetMap;

    public static MemoryOffsetHolder getInstance() {
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        public static final MemoryOffsetHolder instance = new MemoryOffsetHolder();
    }

    private MemoryOffsetHolder() {
        offsetMap = new HashMap<>();
    }

    @Override
    public Long get(@NonNull String task, @NonNull String table, @NonNull String shard) {
        String key = formatKey(task, table, shard);
        if (!OffsetLocker.lock(key)) {
            return null;
        }

        Long offset = offsetMap.get(key);

        OffsetLocker.unlock(key);
        return offset;
    }

    @Override
    public void set(@NonNull String task, @NonNull String table, @NonNull String shard, @NonNull Long id) {
        String key = formatKey(task, table, shard);
        if (!OffsetLocker.lock(key)) {
            return;
        }

        offsetMap.put(key, id);

        OffsetLocker.unlock(key);
    }

}
