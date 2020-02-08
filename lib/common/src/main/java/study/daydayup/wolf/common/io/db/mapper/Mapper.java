package study.daydayup.wolf.common.io.db.mapper;

import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Row;

import java.util.List;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2020/2/8 6:25 下午
 **/
public class Mapper {
    private static final String DEFAULT_TAG_COLUMN = "tags";

    private Row row;

    public Mapper(Row row) {
        this.row = row;
    }

    public Mapper toLocalDate(@NonNull String column) {
        return toLocalDate(column, null);
    }

    public Mapper toLocalDate(@NonNull String column, String newColumn) {

        return this;
    }

    public Mapper toTag() {
        return toTag(DEFAULT_TAG_COLUMN);
    }

    public Mapper toTag(@NonNull String column) {

        return this;
    }

}
