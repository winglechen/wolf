package study.daydayup.wolf.framework.middleware.es.search.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import study.daydayup.wolf.common.io.sql.builder.SqlBuilder;
import study.daydayup.wolf.framework.layer.api.Request;

@Data
@AllArgsConstructor
public class ESQuery implements Request {
    private String query;
    private Integer fetch_size;

    public static ESQuery fromSql(String sql) {
        return new ESQuery( sql, 0);
    }

    public static ESQuery fromSqlBuilder(SqlBuilder builder) {
        if (builder instanceof ESSqlBuilder) {
            return fromESSqlBuilder((ESSqlBuilder) builder);
        }

        return fromSql(builder.getSql());
    }

    public static ESQuery fromESSqlBuilder(ESSqlBuilder builder) {
        return new ESQuery(builder.getSql(), builder.getLimit());
    }
}
