package study.daydayup.wolf.dts.source.offset;


import lombok.NonNull;
import study.daydayup.wolf.common.util.lang.StringUtil;

/**
 * study.daydayup.wolf.framework.layer.dal.scanner
 *
 * @author Wingle
 * @since 2020/2/5 5:27 下午
 **/
public interface OffsetHolder {

    Long get(String source, String table, String shard, String sink);
    int set(String source, String table, String shard, String sink, Long prefOffset, Long newOffset);

    default String formatKey(@NonNull String source, @NonNull String table, @NonNull String shard) {
        return formatKey(source, table, shard, null);
    }

    default String formatKey(@NonNull String source, @NonNull String table, @NonNull String shard, String sink) {
        return StringUtil.joinWith(StringUtil.COLON, source, table, shard, sink);
    }
}
