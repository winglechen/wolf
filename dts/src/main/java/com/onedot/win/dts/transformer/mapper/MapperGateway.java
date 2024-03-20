package com.onedot.win.dts.transformer.mapper;

import lombok.NonNull;
import com.onedot.win.common.io.db.Row;
import com.onedot.win.common.io.db.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * com.onedot.win.common.io.db
 *
 * @author Wingle
 * @since 2020/2/8 6:25 下午
 **/
public class MapperGateway extends AbstractMapper implements Mapper {
    private List<Mapper> mapperList;

    public MapperGateway() {
        mapperList = new ArrayList<>(5);
    }

    @Override
    public void map(@NonNull Row row) {
        if (mapperList.isEmpty()) {
            return;
        }

        for (Mapper mapper : mapperList) {
            mapper.map(row);
        }
    }

    public MapperGateway toLocalDate(@NonNull String column) {
        return toLocalDate(column, null);
    }

    public MapperGateway toLocalDate(@NonNull String column, String newColumn) {
        LocalDateMapper mapper = new LocalDateMapper();

        mapper.init(column, newColumn);
        mapperList.add(mapper);

        return this;
    }

    public MapperGateway toHour(@NonNull String column) {
        return toHour(column, null);
    }

    public MapperGateway toHour(@NonNull String column, String newColumn) {
        HourMapper mapper = new HourMapper();

        mapper.init(column, newColumn);
        mapperList.add(mapper);

        return this;
    }

    public MapperGateway toMinute(@NonNull String column) {
        return toMinute(column, null);
    }

    public MapperGateway toMinute(@NonNull String column, String newColumn) {
        MinuteMapper mapper = new MinuteMapper();

        mapper.init(column, newColumn);
        mapperList.add(mapper);

        return this;
    }

    public MapperGateway rename(@NonNull String column, @NonNull String newColumn) {
        RenameMapper mapper = new RenameMapper();

        mapper.init(column, newColumn);
        mapperList.add(mapper);

        return this;
    }

    public MapperGateway toTag() {
        return toTag(Table.DEFAULT_TAG_COLUMN);
    }

    public MapperGateway toTag(@NonNull String column) {
        TagMapper mapper = new TagMapper();

        mapper.init(column, newColumn);
        mapperList.add(mapper);

        return this;
    }

    public MapperGateway set(@NonNull String column, @NonNull Object value) {
        SetMapper mapper = new SetMapper();

        mapper.init(column, value);
        mapperList.add(mapper);

        return this;
    }

    public MapperGateway addMapper(@NonNull Mapper mapper) {
        mapperList.add(mapper);
        return this;
    }

}
