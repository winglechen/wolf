package study.daydayup.wolf.dts.transformation.mapper;

import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.io.db.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.common.io.db
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
        Mapper mapper = new LocalDateMapper();

        mapper.init(column, newColumn);
        mapperList.add(mapper);

        return this;
    }

    public MapperGateway rename(@NonNull String column, @NonNull String newColumn) {
        Mapper mapper = new RenameMapper();

        mapper.init(column, newColumn);
        mapperList.add(mapper);

        return this;
    }

    public MapperGateway toTag() {
        return toTag(Table.DEFAULT_TAG_COLUMN);
    }

    public MapperGateway toTag(@NonNull String column) {
        Mapper mapper = new TagMapper();

        mapper.init(column, newColumn);
        mapperList.add(mapper);

        return this;
    }

}
