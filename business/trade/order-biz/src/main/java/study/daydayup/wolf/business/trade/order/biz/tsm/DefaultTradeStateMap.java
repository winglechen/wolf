package study.daydayup.wolf.business.trade.order.biz.tsm;

import study.daydayup.wolf.business.trade.api.exception.InvalidTradeStateException;
import study.daydayup.wolf.business.trade.api.state.TradeState;

import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.business.trade.order.biz.tsm
 *
 * @author Wingle
 * @since 2019/12/17 4:33 下午
 **/
public class DefaultTradeStateMap implements TradeStateMap {
    private Map<Integer, TradeState> stateMap;
    public DefaultTradeStateMap() {
        stateMap = new HashMap<>();
    }

    @Override
    public TradeStateMap addState(TradeState state) {
        int key = state.getCode();
        if (key <= 0) {
            throw new InvalidTradeStateException(key);
        }

        stateMap.put(Integer.valueOf(key), state);
        return this;
    }

    @Override
    public TradeState getByCode(int code) {
        if (code <= 0) {
            return null;
        }

        return stateMap.get(Integer.valueOf(code));
    }
}
