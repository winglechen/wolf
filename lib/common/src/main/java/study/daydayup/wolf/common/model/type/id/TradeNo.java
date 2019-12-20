package study.daydayup.wolf.common.model.type.id;

import study.daydayup.wolf.common.model.contract.ID;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * study.daydayup.wolf.common.model.type.id
 *
 * @author Wingle
 * @since 2019/12/20 11:43 上午
 **/
public class TradeNo implements ID {
    private static final int DEFAULT_SHARDS = 1024;
    private int shards;
    private int  dataCenterId;
    private int dataCenterCount;
    private long accountId;
    private long uuid;

    public static TradeNo newInstance() {
        return new TradeNo();
    }

    public TradeNo() {}

    public String create() {
        StringBuilder tradeNo = new StringBuilder(getDatetime());
        tradeNo.append(getShardKey());

        return tradeNo.toString();
    }

    private String getDatetime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        return now.format(formatter);
    }

    private String getShardKey() {
        if (accountId <= 0) {
            return generateShardKey();
        }

        if (shards <= 0 || shards > 9000) {
            shards = DEFAULT_SHARDS;
        }

        long mod = accountId % shards;
        return String.valueOf(mod);
    }

    private String generateShardKey() {
        int key = ThreadLocalRandom.current().nextInt(9000, 9999);
        return String.valueOf(key);
    }

    public static void main(String[] args) {

    }

}
