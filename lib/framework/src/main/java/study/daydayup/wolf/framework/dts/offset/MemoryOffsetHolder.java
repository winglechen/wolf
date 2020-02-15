package study.daydayup.wolf.framework.dts.offset;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

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
    public Long get(@NonNull String source, @NonNull String table, @NonNull String shard, @NonNull String sink) {
        String key = formatKey(source, table, shard, sink);
        if (!OffsetLocker.lock(key)) {
            return null;
        }

        Long offset = offsetMap.get(key);

        OffsetLocker.unlock(key);
        return offset;
    }

    @Override
    public void set(@NonNull String source, @NonNull String table, @NonNull String shard, @NonNull String sink, @NonNull Long id) {
        String key = formatKey(source, table, shard, sink);
        if (!OffsetLocker.lock(key)) {
            return;
        }

        offsetMap.put(key, id);

        OffsetLocker.unlock(key);
    }

}
