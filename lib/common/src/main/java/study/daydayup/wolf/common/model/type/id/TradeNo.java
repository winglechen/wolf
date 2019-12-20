package study.daydayup.wolf.common.model.type.id;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.common.model.contract.ID;
import study.daydayup.wolf.common.util.StringUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * study.daydayup.wolf.common.model.type.id
 *
 * @author Wingle
 * @since 2019/12/20 11:43 上午
 **/
@Data
@Builder
public class TradeNo implements ID {
    public static final int DEFAULT_SHARDS = 1024;
    public static final int SHARD_LENGTH = 4;
    public static final int DATA_CENTER_LENGTH = 4;
    public static final int UUID_LENGTH = 9;

    private int shards;
    private int shard;
    private TradePhaseEnum tradePhase;
    private int  dataCenterId;
    private long accountId;
    private long uuid;

    public String create() {
        StringBuilder tradeNo = new StringBuilder(addDatetime());

        tradeNo.append(addTradePhase());
        tradeNo.append(addShardKey());
        tradeNo.append(addDataCenterKey());

        tradeNo.append(addUUID());
        return tradeNo.toString();
    }

    private String addUUID() {
        if (uuid > 0) {
            //TODO padding length
            return String.valueOf(uuid);
        }

        return generateRandomNum(UUID_LENGTH);
    }

    private String addDataCenterKey() {
        if (dataCenterId >= 9000) {
            throw new IllegalArgumentException("Total data center number can't greater than 9000");
        }

        if (dataCenterId <= 0) {
            return generateRandomNum(4, 9);
        }

        return shardPending(dataCenterId);
    }

    private int addTradePhase() {
        if (tradePhase == null) {
            return 0;
        }

        return tradePhase.getCode();
    }

    private String addDatetime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        return now.format(formatter);
    }

    private String addShardKey() {
        if (accountId <= 0) {
            return generateShardKey();
        }

        if (shards <= 0 || shards >= 10000) {
            shards = DEFAULT_SHARDS;
        }

        shard = (int)accountId % shards;
        return shardPending(shard);
    }

    private String shardPending(int num) {
        if (num < 10) {
            return StringUtil.join("000", num);
        }

        if (num < 100) {
            return StringUtil.join("00", num);
        }

        if (num < 1000) {
            return StringUtil.join("0", num);
        }

        return String.valueOf(num);
    }

    private String generateShardKey() {
        return generateRandomNum(SHARD_LENGTH, 9);
    }

    private String generateRandomNum(int length) {
        return generateRandomNum(length, 1);
    }

    private String generateRandomNum(int length, int step) {
        if (step < 1) {
            throw new IllegalArgumentException("Random num step can't less than 1: " + step);
        }

        if (length <= 0 || length > 20) {
            throw new IllegalArgumentException("Random num length is invalid:" + length);
        }
        length--;

        int start = (int)Math.pow(10, length) * step;
        int end   = (int)Math.pow(10, length + 1) - 1;

        int key = ThreadLocalRandom.current().nextInt(start, end);
        return String.valueOf(key);
    }


    public static void main(String[] args) {
//        System.out.println("TradeNo:" + new TradeNo().create());
//        System.out.println("length:" + "20191220134655094329598966778997".length());


        String tradeNo = TradeNo.builder()
                .accountId(123)
                .dataCenterId(3)
                .tradePhase(TradePhaseEnum.ORDER_PHASE)
                .build()
                .create();
        System.out.println("TradeNo:" + tradeNo);
        System.out.println("length:" + tradeNo.length());


        TradeNoParser parser = new TradeNoParser();
        TradeNo obj = parser.parse(tradeNo);

        if (obj != null) {
            System.out.println("shard:" + obj.getShard());
            System.out.println("phase:" + obj.getTradePhase());
            System.out.println("data center:" + obj.getDataCenterId());
        } else {
            System.out.println("parse error");
        }

    }

}
