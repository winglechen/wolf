package study.daydayup.wolf.framework.layer.converter;

import study.daydayup.wolf.common.lang.contract.worker.Converter;
import study.daydayup.wolf.framework.layer.api.ModelV1;
import study.daydayup.wolf.framework.layer.dal.DataObject;
import study.daydayup.wolf.framework.layer.domain.EntityV1;

/**
 * study.daydayup.wolf.framework.layer.converter
 *
 * @author Wingle
 * @since 2020/1/11 2:23 下午
 **/
public interface EntityConverter extends Converter {
    ModelV1 toModel(EntityV1 entityV1);
    DataObject toDo(EntityV1 entityV1);
}
