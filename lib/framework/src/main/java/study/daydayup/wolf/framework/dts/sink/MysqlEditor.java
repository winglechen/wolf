package study.daydayup.wolf.framework.dts.sink;

import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.daydayup.wolf.common.io.db.Statistics;
import study.daydayup.wolf.framework.dts.offset.Offset;

import javax.annotation.Resource;
import java.util.Map;

/**
 * study.daydayup.wolf.framework.dts.sink
 *
 * @author Wingle
 * @since 2020/2/8 6:07 下午
 **/
@Component
public class MysqlEditor {
    @Resource
    private JdbcTemplate jdbc;

    public int update(@NonNull String table, @NonNull Map<String, Object> key, @NonNull Map<String, Object> value) {

        return 0;
    }

    @Transactional
    public void save(Offset offset, Statistics statistics) {

    }
}
