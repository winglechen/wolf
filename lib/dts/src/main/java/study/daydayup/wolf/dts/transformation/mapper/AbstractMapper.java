package study.daydayup.wolf.dts.transformation.mapper;

import lombok.NonNull;

/**
 * study.daydayup.wolf.dts.transformation.mapper
 *
 * @author Wingle
 * @since 2020/2/11 6:09 下午
 **/
public abstract class AbstractMapper implements Mapper {
    protected String column;
    protected String newColumn;

    @Override
    public void init(@NonNull String column) {
        init(column, null);
    }

    @Override
    public void init(@NonNull String column, String newColumn) {
        this.column = column;
        this.newColumn = newColumn;
    }

}
