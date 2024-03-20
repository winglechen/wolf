package com.onedot.win.framework.util.id;

import com.onedot.win.common.lang.enums.trade.TradePhaseEnum;
import com.onedot.win.common.util.lang.EnumUtil;

/**
 * com.onedot.win.common.model.type.string.id
 *
 * @author Wingle
 * @since 2019/12/20 1:50 下午
 **/
public class TradeNoParser {
    /**
     * 总分片数 (256)
     */
    private final int shards;

    public TradeNoParser() {
        this(0);
    }

    public TradeNoParser(int shards) {
        this.shards = shards;
    }

    public TradeNo parse(String stringNo) {
        if (stringNo.length() == 32) {
            return parse32No(stringNo);
        }

        if (stringNo.length() == 20) {
            return parse20No(stringNo);
        }

        return null;
    }

    private TradeNo parse20No(String stringNo) {
        if (stringNo.length() != 20) {
            return null;
        }

        TradeNo tradeNo = initTradeNo();
        tradeNo.setShard(parse20Shard(stringNo));

        return tradeNo;
    }

    private TradeNo parse32No(String stringNo) {
        if (stringNo.length() != 32) {
            return null;
        }

        TradeNo tradeNo = initTradeNo();

        tradeNo.setShard(parse32Shard(stringNo));
        tradeNo.setTradePhase(parsePhase(stringNo));
        tradeNo.setPhase(parsePhaseString(stringNo));
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

    private int parse20Shard(String stringNo) {
        String s = stringNo.substring(11, 15);
        int num = Integer.parseInt(s);

        return Math.max(num, 0);
    }

    private int parse32Shard(String stringNo) {
        String s = stringNo.substring(15, 19);
        int num = Integer.parseInt(s);

        return Math.max(num, 0);
    }

    @Deprecated
    private TradePhaseEnum parsePhase(String stringNo) {
        String s = stringNo.substring(14, 15);
        int num = Integer.parseInt(s);

        if (num <= 0) {
            return null;
        }

        return EnumUtil.codeOf(num, TradePhaseEnum.class);
    }

    private int parsePhaseString(String stringNo) {
        String phase = stringNo.substring(14, 15) + stringNo.substring(23, 24);
        return Integer.parseInt(phase);
    }

    private int parseDataCenterId(String stringNo) {
        String s = stringNo.substring(24, 26);
        int num = Integer.parseInt(s);

        return Math.max(num, 0);
    }

}
