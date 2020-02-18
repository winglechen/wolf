package study.daydayup.wolf.framework.dts.source.offset;

import lombok.NonNull;
import study.daydayup.wolf.common.util.collection.MapUtil;

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

    public void setAll(@NonNull String source, @NonNull String table, @NonNull String shard, @NonNull Map<String, Long> data) {
        if (MapUtil.isEmpty(data)) {
            return;
        }

        String key = formatKey(source, table, shard);
        if (!OffsetLocker.lock(key)) {
            return;
        }

        offsetMap.putAll(data);

        OffsetLocker.unlock(key);
    }

    @Override
    public int set(@NonNull String source, @NonNull String table, @NonNull String shard, @NonNull String sink, @NonNull Long preOffset, @NonNull Long newOffset) {
        String key = formatKey(source, table, shard, sink);
        if (!OffsetLocker.lock(key)) {
            return 0;
        }

        offsetMap.replace(key, preOffset, newOffset);

        OffsetLocker.unlock(key);
        return 1;
    }

}
