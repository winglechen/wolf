package study.daydayup.wolf.framework.middleware.hbase.option;

import lombok.Data;

/**
 * FindQueryCriteria
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2023/7/26 16:01
 **/
@Data
public class GetOptionCriteria extends OptionCriteria {

    private String rowKey;

}
