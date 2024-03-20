package com.onedot.win.framework.middleware.hbase.option;

import lombok.Data;
import com.onedot.win.framework.middleware.hbase.table.HBaseTable;

/**
 * PutOptionCriteria
 *
 * @author rocky
 * @since 2023/8/3 16:32
 **/
@Data
public class PutOptionCriteria extends OptionCriteria{

    private String rowKey;

    private Object data;
}
