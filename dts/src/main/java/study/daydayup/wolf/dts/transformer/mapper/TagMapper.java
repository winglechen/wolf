package study.daydayup.wolf.dts.transformer.mapper;

import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.model.type.string.Tag;

/**
 * study.daydayup.wolf.dts.transformation.mapper
 *
 * @author Wingle
 * @since 2020/2/11 6:09 下午
 **/
public class TagMapper extends AbstractMapper implements InitableMapper {
    @Override
    public void map(Row row) {
        Object value = row.get(column);
        if (value == null) {
            return;
        }

        row.put(column, new Tag((String) value));
    }
}
