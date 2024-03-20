package com.onedot.win.framework.layer.converter;

import com.onedot.win.common.lang.contract.worker.Converter;
import com.onedot.win.framework.layer.api.ModelV1;
import com.onedot.win.framework.layer.dal.DataObject;
import com.onedot.win.framework.layer.domain.EntityV1;

/**
 * com.onedot.win.framework.layer.converter
 *
 * @author Wingle
 * @since 2020/1/11 2:23 下午
 **/
public interface EntityConverter extends Converter {
    ModelV1 toModel(EntityV1 entityV1);
    DataObject toDo(EntityV1 entityV1);
}
