package study.daydayup.wolf.common.io.db;

import lombok.NonNull;
import study.daydayup.wolf.common.model.type.string.Tag;
import study.daydayup.wolf.common.util.time.DateUtil;

import java.util.Date;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2020/2/8 6:25 下午
 **/
public class Mapper {
    private static final String DEFAULT_TAG_COLUMN = "tags";

    private Row row;

    public Mapper(@NonNull Row row) {
        this.row = row;
    }

    public Mapper toLocalDate(@NonNull String column) {
        return toLocalDate(column, null);
    }

    public Mapper toLocalDate(@NonNull String column, @NonNull String newColumn) {
        Date value = (Date) row.get(column);
        if (value == null) {
            return this;
        }

        String key = null != newColumn ? newColumn : column;
        row.put(key, DateUtil.asLocalDate(value));

        return this;
    }

    public Mapper toTag() {
        return toTag(DEFAULT_TAG_COLUMN);
    }

    public Mapper toTag(@NonNull String column) {
        String value = (String) row.get(column);
        if (value == null) {
            return this;
        }

        row.put(column, new Tag(value));
        return this;
    }

}
