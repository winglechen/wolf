package study.daydayup.wolf.framework.middleware.hbase.option;

import lombok.Data;
import study.daydayup.wolf.framework.middleware.hbase.table.HBaseTable;

/**
 * PutOptionCriteria
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2023/8/3 16:32
 **/
@Data
public class PutOptionCriteria extends OptionCriteria{

    private String rowKey;

    private Object data;
}
