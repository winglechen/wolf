package study.daydayup.wolf.business.trade.api.domain.util;

import study.daydayup.wolf.business.trade.api.domain.state.TradeState;
import study.daydayup.wolf.common.util.collection.ArrayUtil;
import study.daydayup.wolf.common.util.lang.BeanUtil;

/**
 * study.daydayup.wolf.business.trade.api.domain.util
 *
 * @author Wingle
 * @since 2020/3/1 3:24 下午
 **/
public class StateUtil {
    public static  boolean equals(Integer code, TradeState state) {
        return BeanUtil.equals(code, state.getCode());
    }

    public static  boolean equals(TradeState state1, TradeState state2) {
        return BeanUtil.equals(state1.getCode(), state2.getCode());
    }

    public static boolean inArray(TradeState state, TradeState... arr) {
        if (ArrayUtil.isEmpty(arr)) {
            return false;
        }

        for (TradeState s : arr) {
            if (equals(state, s)) {
                return true;
            }
        }

        return false;
    }
}
