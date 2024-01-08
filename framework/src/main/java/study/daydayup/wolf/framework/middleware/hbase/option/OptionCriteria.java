package study.daydayup.wolf.framework.middleware.hbase.option;

import lombok.Data;

/**
 * QueryCriteria
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2023/7/26 15:59
 **/

@Data
public class OptionCriteria {

    private String table;

    private String source;

}
