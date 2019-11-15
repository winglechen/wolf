package study.daydayup.wolf.framework.util.id;

import study.daydayup.wolf.common.lang.string.ID;

/**
 * study.daydayup.wolf.framework.util.id
 *
 * @author Wingle
 * @since 2019/11/15 2:48 下午
 **/
public class TradeNo  implements ID {
    public static final int ORDER       = 0 ;
    public static final int CONTRACT    = 20;
    public static final int PAY         = 30;


    private int tradeType;
    private int dataCenterNo;
    private int shardingKey;
    private int shardingNums;

    public static TradeNo getInstance() {
        return new TradeNo();
    }

    TradeNo() {
        this.tradeType = 0;
        this.dataCenterNo = 0;
        this.shardingKey = 0;
    }

    public TradeNo tradeType(int tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public TradeNo dataCenterNo(int dataCenterNo) {
        this.dataCenterNo = dataCenterNo;
        return this;
    }

    public TradeNo shardingKey(int key) {
        return this.shardingKey((long)key);
    }

    public TradeNo shardingKey(long key) {
        this.shardingKey = 0;
        return this;
    }

    public TradeNo shardingKey(String key) {

        return this;
    }

    public  String build() {
        return "";
    }


}
