package com.onedot.win.framework.layer.converter;

import com.onedot.win.common.lang.contract.worker.Converter;
import com.onedot.win.framework.layer.dal.DataObject;

/**
 * com.onedot.win.framework.layer.converter
 *
 * @author Wingle
 * @since 2020/1/11 2:01 下午
 **/
public interface DolConverter extends Converter {
    DataObject toModel(DataObject dataObject);
}
