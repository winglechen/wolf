package com.wolf.dts.source.offset;

import com.alibaba.fastjson2.JSON;
import com.wolf.common.lang.exception.SystemException;
import com.wolf.common.ds.string.Str;

/**
 * com.wolf.common.sm
 *
 * @author Wingle
 * @since 2019/12/16 11:29 下午
 **/
public class OffsetNotAvailableException extends SystemException {
    public OffsetNotAvailableException(Offset offset) {
        super(1100, Str.join(
                "Offset not available:",
                JSON.toJSONString(offset)
        ));
    }
}
