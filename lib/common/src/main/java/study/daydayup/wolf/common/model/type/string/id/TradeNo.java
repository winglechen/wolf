package study.daydayup.wolf.common.model.type.string.id;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.common.model.contract.ID;
import study.daydayup.wolf.common.util.lang.StringUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * study.daydayup.wolf.common.model.type.string.id
 *
 * tradeNo = Datetime       (14位  必选  格式：yyyyMMddHHmmss )
 *          + TradePhase    (1位   必选  TradePhaseEnum      )
 *          + shardingKey   (4位         hash(accountId)     )
 *          + dataCenterKey (4位                             )
 *          + uuid          (9位   必选                       )
 *          长度合计         (32位  必选长度  14 + 1 + 4 + 5    )
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
    public static final int NANO_LENGTH = 4;

    private int shards;
    private int shard;
    private TradePhaseEnum tradePhase;
    private int  dataCenterId;
    private long accountId;
    private long uuid;
    private long nano;

    public static TradeNo of(String stringNo) {
        return of(stringNo, 0);
    }

    public static TradeNo of(String stringNo, int shards) {
        return new TradeNoParser(shards).parse(stringNo);
    }

    public String create() {
        StringBuilder tradeNo;
        tradeNo = new StringBuilder(addDatetime());

        tradeNo.append(addTradePhase());
        tradeNo.append(addShardKey());
        tradeNo.append(addDataCenterKey());

        tradeNo.append(addUuid());
        return tradeNo.toString();
    }

    private String addUuid() {
        if (uuid > 0) {
            //TODO padding length
            return String.valueOf(uuid);
        }

        String ns = String.valueOf(getNano());
        int nsLen = ns.length();
        if (nsLen > 6) {
            ns = ns.substring(0, NANO_LENGTH);
            nsLen = NANO_LENGTH;
        }

        int randLen = UUID_LENGTH - nsLen;
        if (randLen < 1) {
            return ns;
        }

        String rand = generateRandomNum(randLen);
        return StringUtil.join(ns, rand);
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

    private long getNano() {
        if (0 != nano) {
            return nano;
        }

        nano = System.currentTimeMillis();
        return nano;
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
}
