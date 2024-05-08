package com.wolf.framework.middleware.binlog.mysql;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * FieldEntryHolder
 *
 * @author: YIK
 * @since: 2022/3/1 10:18 AM
 */
public class FieldEntryHolder {
    private final List<Object> originFields;
    private final Iterator<Object> iterator;
    private final List<Object> filteredFields;

    public FieldEntryHolder(List<Object> originFields) {
        this.originFields = originFields;
        if (null == originFields) {
            this.filteredFields = null;
            this.iterator = null;
        } else {
            this.filteredFields = new LinkedList<>();
            this.iterator = originFields.iterator();
        }
    }

    public boolean hasNext() {
        if (iterator == null) {
            return false;
        }
        return iterator.hasNext();
    }

    public void skip() {
        if (null != iterator) {
            iterator.next();
        }
    }

    public Object take() {
        if (null != iterator) {
            Object current = iterator.next();
            filteredFields.add(current);
            return current;
        } else {
            return null;
        }
    }
}