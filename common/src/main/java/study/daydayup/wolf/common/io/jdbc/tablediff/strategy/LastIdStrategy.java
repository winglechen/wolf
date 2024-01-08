package study.daydayup.wolf.common.io.jdbc.tablediff.strategy;

import study.daydayup.wolf.common.io.enums.OrderEnum;
import study.daydayup.wolf.common.io.sql.Sql;

public class LastIdStrategy implements TableStrategy {
    private String tableName;

    @Override
    public String getSql(String tableName) {
        this.tableName = tableName;

        return Sql.select("id", false)
                .from(tableName)
                .orderBy("id", OrderEnum.DESC)
                .limit(1)
                .getSql()
                ;
    }

}
