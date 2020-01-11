package study.daydayup.wolf.framework.layer.converter;

import study.daydayup.wolf.framework.layer.api.Model;
import study.daydayup.wolf.framework.layer.dal.DataObject;
import study.daydayup.wolf.framework.layer.domain.Entity;

/**
 * study.daydayup.wolf.framework.layer.converter
 *
 * @author Wingle
 * @since 2020/1/11 2:23 下午
 **/
public interface EntityConverter extends Converter {
    Model toModel(Entity entity);
    DataObject toDo(Entity entity);
}
