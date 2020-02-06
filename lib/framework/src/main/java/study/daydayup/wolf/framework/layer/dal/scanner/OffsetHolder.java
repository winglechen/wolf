package study.daydayup.wolf.framework.layer.dal.scanner;


import lombok.NonNull;
import study.daydayup.wolf.common.util.StringUtil;

/**
 * study.daydayup.wolf.framework.layer.dal.scanner
 *
 * @author Wingle
 * @since 2020/2/5 5:27 下午
 **/
public interface OffsetHolder {
    Long get(String task, String table, String shard);
    void set(String task, String table, String shard, Long id);

    default String formatKey(@NonNull String task, @NonNull String table, @NonNull String shard) {
        return StringUtil.join(StringUtil.COLON, task, table, shard);
    }
}
