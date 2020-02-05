package study.daydayup.wolf.framework.layer.dal.scanner;

import lombok.NonNull;
import study.daydayup.wolf.common.util.StringUtil;

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
public class OffsetHolder {
    private Lock locker;
    private Map<String, Long> offsetMap;

    public static OffsetHolder getInstance() {
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        public static final OffsetHolder instance = new OffsetHolder();
    }

    private OffsetHolder() {
        locker = new ReentrantLock();
        offsetMap = new HashMap<>();
    }

    public Long get(@NonNull String table, @NonNull String shard) {
        if (!locker.tryLock()) {
            return null;
        }

        String key = formatKey(table, shard);
        Long offset = offsetMap.get(key);

        locker.unlock();
        return offset;
    }

    public void set(@NonNull String table, @NonNull String shard, @NonNull Long id) {
        if (!locker.tryLock()) {
            return;
        }

        String key = formatKey(table, shard);
        offsetMap.put(key, id);

        locker.unlock();
    }

    private String formatKey(@NonNull String table, @NonNull String shard) {
        return StringUtil.join(StringUtil.COLON, table, shard);
    }

}
