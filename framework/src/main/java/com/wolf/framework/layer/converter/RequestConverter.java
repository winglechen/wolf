package com.wolf.framework.layer.converter;

import com.wolf.common.lang.contract.worker.Converter;
import com.wolf.framework.layer.api.Request;

/**
 * com.wolf.framework.layer.converter
 *
 * @author Wingle
 * @since 2020/1/11 2:05 下午
 **/
public interface RequestConverter extends Converter {
    Request toEpi(Request request);
}
