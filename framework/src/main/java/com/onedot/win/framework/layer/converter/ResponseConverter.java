package com.onedot.win.framework.layer.converter;

import com.onedot.win.common.lang.contract.worker.Converter;
import com.onedot.win.framework.layer.api.ModelV1;
import com.onedot.win.framework.layer.api.Response;

/**
 * com.onedot.win.framework.layer.converter
 *
 * @author Wingle
 * @since 2020/1/11 2:05 下午
 **/
public interface ResponseConverter extends Converter {
    ModelV1 toModel(Response response);
}
