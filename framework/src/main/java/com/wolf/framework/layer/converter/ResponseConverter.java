package com.wolf.framework.layer.converter;

import com.wolf.common.lang.contract.worker.Converter;
import com.wolf.framework.layer.api.ModelV1;
import com.wolf.framework.layer.api.Response;

/**
 * com.wolf.framework.layer.converter
 *
 * @author Wingle
 * @since 2020/1/11 2:05 下午
 **/
public interface ResponseConverter extends Converter {
    ModelV1 toModel(Response response);
}
