package study.daydayup.wolf.common.io.db;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * com.daydayup.learn.hello.common.lang.ds
 *
 * @author Wingle
 * @since 2019/7/12 10:30 AM
 **/
@Data
public class Table extends LinkedList<Row>{

    public static Table of(List<Map<String,Object>> table) {
        return new Table(table);
    }

    Table() {
        super();
    }

    Table(List<Map<String,Object>> table) {
        union(table);
    }

    public void join(List<Map<String,Object>> table, String byColumn) {
    }

    public void union(List<Map<String,Object>> table) {
        union(table, UnionEnum.BOTTOM);
    }

    public void union(List<Map<String,Object>> table, UnionEnum direction) {
        for (Map<String, Object> item: table) {
            switch (direction) {
                case TOP:
                    addFirst(Row.of(item));
                    break;
                case BOTTOM:
                    addLast(Row.of(item));
                    break;
            }
        }
    }




}

