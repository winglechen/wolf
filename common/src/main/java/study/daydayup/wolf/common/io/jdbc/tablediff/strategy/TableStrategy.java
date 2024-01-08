package study.daydayup.wolf.common.io.jdbc.tablediff.strategy;

public interface TableStrategy {
    String getSql(String tableName);
}
