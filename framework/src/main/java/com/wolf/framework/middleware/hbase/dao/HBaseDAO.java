package com.wolf.framework.middleware.hbase.dao;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import com.wolf.framework.middleware.hbase.HBase;
import com.wolf.framework.middleware.hbase.dao.exception.HBaseDAOException;
import com.wolf.framework.middleware.hbase.option.*;
import com.wolf.framework.middleware.hbase.result.HBaseResult;
import com.wolf.framework.middleware.hbase.table.HBaseTable;

import java.util.List;

/**
 * HBaseDAO
 *
 * @author rocky
 * @since 2023/8/3 16:22
 **/
@Slf4j
public abstract class HBaseDAO<T extends HBaseTable> {

    @Getter
    private Class<T> tableClass;

    public T find(String rowKey) {
        GetOptionCriteria findOptionCriteria = new GetOptionCriteria();
        findOptionCriteria.setRowKey(rowKey);
        HBaseResult<T> result = HBase.get(findOptionCriteria).table(getTableClass()).execute();
        if (result.isSuccess()) {
            return result.getData();
        }
        throw new HBaseDAOException(result.getMessage());
    }

    public List<T> findList(List<String> rowKeys) {
        GetsOptionCriteria getsOption = new GetsOptionCriteria();
        getsOption.setRowKeys(rowKeys);
        HBaseResult result = HBase.gets(getsOption).table(getTableClass()).execute();
        if (result.isSuccess() && result.getData() instanceof List list) {
            return list;
        }
        throw new HBaseDAOException(result.getMessage());
    }

    public List<T> query(String startRowKey, String stopRowKey, Integer limit, Boolean includeStart) {
        ScanOptionCriteria scanOption = new ScanOptionCriteria();
        scanOption.setStartRowKey(startRowKey);
        scanOption.setStopRowKey(stopRowKey);
        scanOption.setLimit(limit);
        scanOption.setIncludeStartRow(includeStart);
        HBaseResult result = HBase.scan(scanOption).table(getTableClass()).execute();
        if (result.isSuccess() && result.getData() instanceof List list) {
            return list;
        }
        throw new HBaseDAOException(result.getMessage());
    }

    public boolean save(String rowKey, T data) {
        PutOptionCriteria putOptionCriteria = new PutOptionCriteria();
        putOptionCriteria.setRowKey(rowKey);
        putOptionCriteria.setData(data);
        HBaseResult result = HBase.put(putOptionCriteria).table(getTableClass()).execute();
        return result.isSuccess();
    }

    public boolean batchSave(List<String> rowKeys, List<T> data) {
        PutsOptionCriteria puts = new PutsOptionCriteria();
        puts.setRowKeys(rowKeys);
        puts.setData(data);
        HBaseResult result = HBase.puts(puts).table(getTableClass()).execute();
        return result.isSuccess();
    }


}
