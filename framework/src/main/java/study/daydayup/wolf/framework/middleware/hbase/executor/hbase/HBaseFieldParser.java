package study.daydayup.wolf.framework.middleware.hbase.executor.hbase;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;

/**
 * HBaseFieldParser
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2023/7/27 15:50
 **/
@Data
@AllArgsConstructor
public class HBaseFieldParser {
    private Field field;

    private String family;

    private String qualifier;
}
