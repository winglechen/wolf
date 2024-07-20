package com.wolf.dts.source;

import lombok.NonNull;
import com.wolf.common.io.db.Table;

/**
 *
 * @author Wingle
 * @since 2020/2/4 5:59 下午
 **/
public interface DbScanner {
    Table scan(@NonNull String table, @NonNull Long id);
    Table scan(@NonNull String table, @NonNull Long id, @NonNull String columns);
}
