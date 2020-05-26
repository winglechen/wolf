package study.daydayup.wolf.common.model.type.string.id;

import org.junit.Test;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.common.model.type.string.id
 *
 * @author Wingle
 * @since 2020/5/25 9:28 上午
 **/
public class TradeNoTest {

    @Test
    public void create() {
    }

    private String createTradeNo() {
        return TradeNo.builder()
                .tradePhase(TradePhaseEnum.ORDER_PHASE)
                .accountId(10001)
                .build()
                .create();
    }
}