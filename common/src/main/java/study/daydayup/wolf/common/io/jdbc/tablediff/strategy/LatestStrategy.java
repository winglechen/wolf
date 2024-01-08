package study.daydayup.wolf.common.io.jdbc.tablediff.strategy;

import study.daydayup.wolf.common.io.enums.OrderEnum;
import study.daydayup.wolf.common.io.sql.Sql;

public class LatestStrategy implements TableStrategy {

    @Override
    public String getSql(String tableName) {

        return Sql.select("*", false)
                .from(tableName)
                .orderBy("id", OrderEnum.DESC)
                .limit(100)
                .getSql()
                ;
    }

}
