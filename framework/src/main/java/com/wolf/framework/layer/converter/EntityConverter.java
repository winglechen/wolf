package com.wolf.framework.layer.converter;

import com.wolf.common.lang.contract.worker.Converter;
import com.wolf.framework.layer.api.ModelV1;
import com.wolf.framework.layer.dal.DataObject;
import com.wolf.framework.layer.domain.EntityV1;

/**
 * com.wolf.framework.layer.converter
 *
 * @author Wingle
 * @since 2020/1/11 2:23 下午
 **/
public interface EntityConverter extends Converter {
    ModelV1 toModel(EntityV1 entityV1);
    DataObject toDo(EntityV1 entityV1);
}
