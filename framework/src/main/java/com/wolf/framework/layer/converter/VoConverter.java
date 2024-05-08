package com.wolf.framework.layer.converter;

import com.wolf.common.lang.contract.worker.Converter;
import com.wolf.framework.layer.dal.DataObject;
import com.wolf.framework.layer.domain.VO;

/**
 * com.wolf.framework.layer.converter
 *
 * @author Wingle
 * @since 2020/1/11 2:25 下午
 **/
public interface VoConverter extends Converter {
    DataObject toDo(VO vo);
}
