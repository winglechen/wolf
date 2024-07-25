package com.wolf.wolfno.wolfid;

import com.wolf.common.lang.exception.SystemException;
import com.wolf.wolfno.config.WolfNoConfig;
import com.wolf.wolfno.model.WolfNoContext;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
public class WolfIDFetcher {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final static String SCHEMA_TABLE = "information_schema.tables ";
    private final static String UID_TABLE = "wolf_id";
    private final static int DEFAULT_ID = 11111111;

    private final WolfNoConfig config;
    private final JdbcTemplate jdbcTemplate;

    public WolfIDFetcher(WolfNoConfig config, JdbcTemplate jdbcTemplate) {
        this.config = config;
        this.jdbcTemplate = jdbcTemplate;
    }

    private boolean checkTableExists() {
        String sql = """
            SELECT table_name FROM %s
            WHERE table_schema = 'public'
            AND table_name = '%s'
        """.formatted(SCHEMA_TABLE, UID_TABLE);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);

        return !result.isEmpty();
    }

    public IDResult getID(WolfNoContext context, int step) {
        String name = context.getName();
        name += FORMATTER.format(context.getCreateTime());

        String sql = """
            INSERT INTO %s (name, uid) values ('%s', %d)
            ON CONFLICT(name)
            DO UPDATE set uid = %s.uid + %d
            RETURNING uid
        """.formatted(UID_TABLE, name, DEFAULT_ID, UID_TABLE, step);

        Integer id = jdbcTemplate.queryForObject(sql, Integer.class);
        if (null == id) {
            throw new SystemException("get wolfID failed");
        }

        return null;
    }

}
