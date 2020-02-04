package study.daydayup.wolf.framework.layer.dal.scanner;

import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.io.enums.OrderEnum;

/**
 * study.daydayup.wolf.framework.layer.dal.scanner
 *
 * @author Wingle
 * @since 2020/2/4 5:59 下午
 **/
public interface DbScanner {
    Table scan(@NonNull String table, @NonNull Long from);
    Table scan(@NonNull String table, @NonNull Long from, int limit);
    Table scan(@NonNull String table, @NonNull Long from, int limit, OrderEnum order);
}
