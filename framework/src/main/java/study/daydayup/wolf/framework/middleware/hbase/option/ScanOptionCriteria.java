package study.daydayup.wolf.framework.middleware.hbase.option;

import lombok.Data;

/**
 * ScanQueryCriteria
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2023/7/26 16:02
 **/
@Data
public class ScanOptionCriteria extends OptionCriteria {

    private String startRowKey;

    private Boolean includeStartRow = true;

    private String stopRowKey;

    private Integer limit;
}
