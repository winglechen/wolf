package com.wolf.dts.source.offset;

import com.alibaba.fastjson2.JSON;
import com.wolf.common.lang.exception.SystemException;
import com.wolf.common.lang.string.Str;

/**
 * com.wolf.common.sm
 *
 * @author Wingle
 * @since 2019/12/16 11:29 下午
 **/
public class OffsetLockFailException extends SystemException {
    public OffsetLockFailException(Offset offset) {
        super(1100, Str.join(
                "Offset lock fail:",
                JSON.toJSONString(offset)
        ));
    }
}
