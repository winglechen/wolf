package com.wolf.framework.layer.domain.uid;

import com.wolf.common.lang.exception.SystemException;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
public class WolfID {
    private final static String SCHEMA_TABLE = "information_schema.tables ";
    private final static String UID_TABLE = "wolf_id";
    private final static int DEFAULT_ID = 11111111;

    private final JdbcTemplate jdbcTemplate;
    private boolean enable = false;

    public WolfID(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.enable = checkTableExists();
    }

    private boolean checkTableExists() {
        String sql = """
            SELECT table_name FROM %s 
            WHERE table_schema = 'public' 
            AND table_name = '%s'
        """.formatted(SCHEMA_TABLE, UID_TABLE);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);

        return result.size() > 0;
    }

    public int getID(String name, int step) {
        if (!this.enable) {
            throw new SystemException("WolfID table was not found!");
        }

        String sql = """
            INSERT INTO %s (name, uid) values ('%s', %d)
            ON CONFLICT(name)
            DO UPDATE set uid = excluded.uid + %d
            RETURNING uid
        """.formatted(UID_TABLE, name, DEFAULT_ID, step);

        try {
            return jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (Exception e) {
            throw new SystemException("get wolfID failed: " + e.getMessage());
        }
    }

}
