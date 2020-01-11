package study.daydayup.wolf.framework.layer.converter;

import study.daydayup.wolf.framework.layer.dal.DataObject;
import study.daydayup.wolf.framework.layer.domain.VO;

/**
 * study.daydayup.wolf.framework.layer.converter
 *
 * @author Wingle
 * @since 2020/1/11 2:25 下午
 **/
public interface VoConverter extends Converter {
    DataObject toDo(VO vo);
}
