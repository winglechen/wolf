package com.wolf.framework.middleware.hbase.option;

import lombok.Data;
import com.wolf.framework.middleware.hbase.table.HBaseTable;

import java.util.List;

/**
 * PutsOptionCriteria
 *
 * @author rocky
 * @since 2023/8/7 15:27
 **/
@Data
public class PutsOptionCriteria extends OptionCriteria{
    private List data;

    private List<String> rowKeys;
}
