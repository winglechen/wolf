package com.onedot.win.dts.source.offset;

import com.alibaba.fastjson.JSON;
import com.onedot.win.common.lang.exception.SystemException;
import com.onedot.win.common.lang.string.Str;

/**
 * com.onedot.win.common.sm
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
