package study.daydayup.wolf.common.model.type.string.id;

import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.common.util.lang.EnumUtil;

/**
 * study.daydayup.wolf.common.model.type.string.id
 *
 * @author Wingle
 * @since 2019/12/20 1:50 下午
 **/
public class TradeNoParser {
    private int shards;

    public TradeNoParser() {
        this(0);
    }

    public TradeNoParser(int shards) {
        this.shards = shards;
    }

    public TradeNo parse(String stringNo) {
        if (stringNo.length() != 32) {
            return null;
        }

        TradeNo tradeNo = initTradeNo();

        tradeNo.setShard(parseShard(stringNo));
        tradeNo.setTradePhase(parsePhase(stringNo));
        tradeNo.setDataCenterId(parseDataCenterId(stringNo));

        return tradeNo;
    }

    private TradeNo initTradeNo() {
        TradeNo tradeNo = TradeNo.builder().build();

        if (shards > 0 && shards < 10000) {
            tradeNo.setShards(shards);
        }

        return tradeNo;
    }

    private int parseShard(String stringNo) {
        String s = stringNo.substring(15, 19);
        int num = Integer.parseInt(s);

        return Math.max(num, 0);
    }

    private TradePhaseEnum parsePhase(String stringNo) {
        String s = stringNo.substring(14, 15);
        int num = Integer.parseInt(s);

        if (num <= 0) {
            return null;
        }

        return EnumUtil.codeOf(num, TradePhaseEnum.class);
    }

    private int parseDataCenterId(String stringNo) {
        String s = stringNo.substring(19, 23);
        int num = Integer.parseInt(s);

        return Math.max(num, 0);
    }

}
