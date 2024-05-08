package com.wolf.framework.middleware.hbase.option;

import lombok.Data;

/**
 * FindQueryCriteria
 *
 * @author rocky
 * @since 2023/7/26 16:01
 **/
@Data
public class GetOptionCriteria extends OptionCriteria {

    private String rowKey;

}
