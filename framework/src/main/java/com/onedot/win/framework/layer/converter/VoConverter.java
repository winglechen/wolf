package com.onedot.win.framework.layer.converter;

import com.onedot.win.common.lang.contract.worker.Converter;
import com.onedot.win.framework.layer.dal.DataObject;
import com.onedot.win.framework.layer.domain.VO;

/**
 * com.onedot.win.framework.layer.converter
 *
 * @author Wingle
 * @since 2020/1/11 2:25 下午
 **/
public interface VoConverter extends Converter {
    DataObject toDo(VO vo);
}
