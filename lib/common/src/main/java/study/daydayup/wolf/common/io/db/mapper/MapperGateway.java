package study.daydayup.wolf.common.io.db.mapper;

import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.model.type.string.Tag;
import study.daydayup.wolf.common.util.time.DateUtil;

import java.util.Date;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2020/2/8 6:25 下午
 **/
public class MapperGateway implements Mapper {
    private Row row;

    public MapperGateway() {
    }

    public MapperGateway(@NonNull Row row) {
        this.row = row;
    }


    @Override
    public void map(Row row) {

    }

    public MapperGateway toLocalDate(@NonNull String column) {
        return toLocalDate(column, null);
    }

    public MapperGateway toLocalDate(@NonNull String column, @NonNull String newColumn) {
        Date value = (Date) row.get(column);
        if (value == null) {
            return this;
        }

        String key = null != newColumn ? newColumn : column;
        row.put(key, DateUtil.asLocalDate(value));

        return this;
    }

    public MapperGateway rename(@NonNull String column, @NonNull String newColumn) {
        Object value = row.get(column);
        if (value == null) {
            return this;
        }

        row.remove(column);
        row.put(newColumn, value);
        return this;
    }

    public MapperGateway toTag() {
        return toTag(Table.DEFAULT_TAG_COLUMN);
    }

    public MapperGateway toTag(@NonNull String column) {
        String value = (String) row.get(column);
        if (value == null) {
            return this;
        }

        row.put(column, new Tag(value));
        return this;
    }

}
