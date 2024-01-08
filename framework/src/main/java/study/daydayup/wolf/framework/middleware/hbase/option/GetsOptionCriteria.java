package study.daydayup.wolf.framework.middleware.hbase.option;

import lombok.Data;

import java.util.List;

/**
 * GetsOptionCriteria
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2023/8/7 15:27
 **/
@Data
public class GetsOptionCriteria extends OptionCriteria{
    private List<String> rowKeys;
}
