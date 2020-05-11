package study.daydayup.wolf.common.model.type.string.id;

import org.junit.Test;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.common.model.type.string.id
 *
 * @author Wingle
 * @since 2019/12/20 3:07 下午
 **/
public class TradeNoParserTest {

    @Test
    public void parse() {
        int shard           = 123;
        int dataCenterId    = 3;
        TradePhaseEnum phase= TradePhaseEnum.ORDER_PHASE;

        String tradeNo = TradeNo.builder()
                .accountId(shard)
                .dataCenterId(dataCenterId)
                .tradePhase(phase)
                .build()
                .create();

        TradeNoParser parser = new TradeNoParser();
        TradeNo obj = parser.parse(tradeNo);

        assertNotNull("tradeNo parse error", obj);
        assertEquals("parse phase fail", phase, obj.getTradePhase());
        assertEquals("parse shard fail", shard, obj.getShard());
        assertEquals("parse data center fail", dataCenterId, obj.getDataCenterId());
    }

    @Test
    public void parse_case2() {
        int shards          = 8;
        int accountId       = 20;
        int shard           = 4;
        int dataCenterId    = 3;
        TradePhaseEnum phase= TradePhaseEnum.ORDER_PHASE;

        String tradeNo = TradeNo.builder()
                .shards(shards)
                .accountId(accountId)
                .dataCenterId(dataCenterId)
                .tradePhase(phase)
                .build()
                .create();

        TradeNoParser parser = new TradeNoParser(shards);
        TradeNo obj = parser.parse(tradeNo);

        assertNotNull("tradeNo parse error", obj);
        assertEquals("parse phase fail", phase, obj.getTradePhase());
        assertEquals("parse shard fail", shard, obj.getShard());
        assertEquals("parse data center fail", dataCenterId, obj.getDataCenterId());
    }

    @Test
    public void parse_case3() {
        int shards          = 16;
        int accountId       = 17;
        int shard           = 1;
        int dataCenterId    = 3;
        TradePhaseEnum phase= TradePhaseEnum.ORDER_PHASE;

        String tradeNo = TradeNo.builder()
                .shards(shards)
                .accountId(accountId)
                .dataCenterId(dataCenterId)
                .tradePhase(phase)
                .build()
                .create();

        TradeNoParser parser = new TradeNoParser(shards);
        TradeNo obj = parser.parse(tradeNo);

        assertNotNull("tradeNo parse error", obj);
        assertEquals("parse phase fail", phase, obj.getTradePhase());
        assertEquals("parse shard fail", shard, obj.getShard());
        assertEquals("parse data center fail", dataCenterId, obj.getDataCenterId());
    }
}