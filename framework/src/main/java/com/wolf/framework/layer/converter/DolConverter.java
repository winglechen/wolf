package com.wolf.framework.layer.converter;

import com.wolf.common.lang.contract.worker.Converter;
import com.wolf.framework.layer.dal.DataObject;

/**
 * com.wolf.framework.layer.converter
 *
 * @author Wingle
 * @since 2020/1/11 2:01 下午
 **/
public interface DolConverter extends Converter {
    DataObject toModel(DataObject dataObject);
}
