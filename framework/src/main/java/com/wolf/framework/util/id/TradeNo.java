package com.wolf.framework.util.id;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import com.wolf.common.lang.exception.SystemException;
import com.wolf.framework.exception.ClockBackwardsException;
import com.wolf.framework.exception.ClockTooManyBackwardsException;
import com.wolf.framework.util.machine.MachineRegister;
import com.wolf.common.lang.enums.trade.TradePhaseEnum;
import com.wolf.common.model.contract.ID;
import com.wolf.common.util.lang.StringUtil;
import com.wolf.common.util.time.DateUtil;
import com.wolf.framework.util.ContextUtil;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * com.wolf.common.model.type.string.id
 * <p>
 * tradeNo = Datetime       (14位  必选  格式：yyyyMMddHHmmss ) 0-14
 * + TradePhase    (1位   必选                      ) 14-15
 * + shardingKey   (4位         hash(accountId)     ) 15-19
 * + machineId     (4位                             ) 19-23
 * + TradePhase    (1位   必选                       ) 23-24
 * + dataCenterKey (2位                             ) 24-26
 * + uuid          (6位   必选                       ) 26-
 * 长度合计         (32位  必选长度  14 + 1 + 4 + 5    )
 *
 * @author Wingle
 * @since 2019/12/20 11:43 上午
 **/
@Data
@Builder
@Slf4j
public class TradeNo implements ID {
    private static final Pattern TRADE_NO_PATTERN = Pattern.compile("^(\\d{20}|\\d{32})$");
    public static final String DATE_FORMAT = "yyyyMMdd";

    public static final int DEFAULT_SHARDS = 1024;
    public static final int SHARD_LENGTH = 4;
    public static final int DATA_CENTER_LENGTH = 1;
    public static final int MACHINE_LENGTH = 4;
    public static final int TUID_LENGTH = 6;
    public static final int NANO_PREFIX_LENGTH = 3;
    public static final int NANO_SUFFIX_LENGTH = 3;

    /**
     * tuid 长6位，理论上真实的最大值为999999,为保险起见，阀值提前到900000
     */
    public static final int MAX_TUID = 900000;

    /**
     * 总分片数 (256)
     */
    private int shards;
    /**
     * 当前所在分片
     */
    private int shard;
    @Deprecated
    private TradePhaseEnum tradePhase;
    private int phase;
    private int dataCenterId;
    private int machineId;
    private long accountId;
    private long uuid;
    private long nano;

    public String create() {

        String dateTime;
        String leftPhase;
        String shardKey;
        String machineId;
        String rightPhase;
        String dateCenterKey;
        String tuid;

        String[] highLow = fixHighLowBit();

        dateTime = highLow[0];
        tuid = highLow[1];
        leftPhase = shapeLeftPhase();
        shardKey = shapeShardKey();
        machineId = shapeMachineId();
        rightPhase = shapeRightPhase();
        dateCenterKey = shapeDataCenterKey();


        /**
         * 1. Define 32-char length to avoid expansion and waste memory
         * 2. Must be appended in the following order
         */
        StringBuilder tradeNo = new StringBuilder(32);
        tradeNo.append(dateTime);
        tradeNo.append(leftPhase);
        tradeNo.append(shardKey);
        tradeNo.append(machineId);
        tradeNo.append(rightPhase);
        tradeNo.append(dateCenterKey);
        tradeNo.append(tuid);
        return tradeNo.toString();

    }


    public static String recreate(@NonNull String tradeNo) {
        int len = tradeNo.length();
        if (len < 5) {
            return null;
        }

        log.warn("TradeNo should not have key duplication,but it happened.");
        String[] highLow = fixHighLowBit();
        String dateTime = highLow[0];
        String tuid = highLow[1];
        return StringUtil.join(dateTime, tradeNo.substring(dateTime.length(), len - 6), tuid);
    }


    /**
     * Generate sequence number as the low-bit,and dateTime as the high-bit
     * 1. Must use synchronized block here
     * 2. Must use static `lock`, because the TradeNo object is not a singleton, but the tuid sequence and the lastTimestamp variable are static!
     * 3. Call nextTuidSync to fix the high-bit Date and the low-bit sequence number
     *
     * @return
     */
    private static String[] fixHighLowBit() {
        String dateTime;
        String tuid;
        synchronized (lock) {
            tuid = nextTuidSyncWait();
            dateTime = shapeDatetime();
        }
        return new String[]{dateTime, tuid};
    }

    public static TradeNo of(String stringNo) {
        return of(stringNo, 0);
    }

    public static TradeNo of(String stringNo, int shards) {
        return new TradeNoParser(shards).parse(stringNo);
    }

    public static boolean isValid(String stringNo) {
        Matcher m = TRADE_NO_PATTERN.matcher(stringNo);
        if (!m.find()) {
            return false;
        }

        try {
            DateUtil.asLocalDate(stringNo.substring(0, 8), DATE_FORMAT);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isLikeValid(String stringNo) {
        int len = stringNo.length();
        if (len != 20 && len != 32) {
            return false;
        }

        if (!StringUtils.isNumeric(stringNo)) {
            return false;
        }

        return true;
    }

    public boolean inExtendableShards() {
        int shard = this.getShard();

        return shard >= DEFAULT_SHARDS;
    }

    private static final byte[] lock = new byte[0];
    private static int sequence;
    private static long lastTimestamp = -1L;


    public String createMinNo() {
        StringBuilder tradeNo;
        tradeNo = new StringBuilder();
        //1
        //tradeNo.append(ThreadLocalRandom.current().nextInt(1, 9));
        tradeNo.append(shapeLeftPhase());
        //10
        tradeNo.append(addMinTime());
        //4
        tradeNo.append(shapeShardKey());
        // todolist@no
        tradeNo.append(randomNum(5));
        return tradeNo.toString();
    }

    private static String shapeMachineId() {
        MachineRegister machineRegister = ContextUtil.getBean(MachineRegister.class);
        int machineId = machineRegister.getLocalMachineId();
        return StringUtils.leftPad(machineId + "", MACHINE_LENGTH, "0");

    }


    private static Long getCurrentTime() {
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }

    private static long nextTime() {
        long time = getCurrentTime();
        while (time <= lastTimestamp) {
            time = getCurrentTime();
        }
        return time;
    }

    /**
     * 包装nextTuid方法，当发生nextTuid发生时钟回拔时同步等待，直到时间追上来
     *
     * @return
     */
    private static String nextTuidSyncWait() {
        try {
            return nextTuid();
        } catch (ClockBackwardsException e) {
            waitFor(System.currentTimeMillis());
            return nextTuid();
        }
    }

    private static String nextTuid() throws ClockBackwardsException, ClockTooManyBackwardsException {
        long currentTimestamp = getCurrentTime();
        if (currentTimestamp < lastTimestamp) {
            throw new ClockBackwardsException(lastTimestamp, currentTimestamp);
        }
        if (currentTimestamp == lastTimestamp && sequence >= MAX_TUID) {
            log.warn("TradeNo sequence overflow MAX_TUID: {} in some second. wait until the next second.", MAX_TUID);
            currentTimestamp = nextTime();
        }
        if (currentTimestamp >= lastTimestamp && sequence >= MAX_TUID) {
            sequence = 0;
        }

        sequence = (sequence + 1);
        lastTimestamp = currentTimestamp;


        return StringUtils.leftPad(sequence + "", TUID_LENGTH, '0');

    }

    /**
     * 单位毫秒
     * 时钟回拨<=10毫秒,使用while循环等待，继续占有CPU；
     * 时钟回拨>10毫秒，使用sleep放弃CPU,但会有上下文切换开销
     */
    private final static int spinThreshold = 10;

    /**
     * 允许的运行时时钟回拨阀值，当大于该值时，抛异常，避免等待时间过长
     * 启动时机器时钟回拨不在考虑范围内
     */
    private final static int brokenThreshold = 500;

    /***
     * 获取时钟回拨了多少，此处使用毫秒单位，不能用秒
     * @param lastTimestamp
     * @return
     */
    private static long getBackwardsTimeStamp(long lastTimestamp) {
        long backwardsStamp = lastTimestamp - System.currentTimeMillis();
        return backwardsStamp;
    }

    /**
     * 等待时间追上，自适应两种等待策略，当回拔的时差在spinThreshold以内时，使用while循环；超过时，使用Thread.sleep等待策略
     *
     * @param lastTimestamp
     */
    private static void waitFor(long lastTimestamp) {
        long backwardsStamp = lastTimestamp - System.currentTimeMillis();
        if (backwardsStamp <= 0) {
            return;
        }

        log.warn("Runtime ClockBackward occur, Sync backwardsStamp:[{}] - lastStamp:[{}].", backwardsStamp, lastTimestamp);

        if (backwardsStamp <= spinThreshold) {
            while ((getBackwardsTimeStamp(lastTimestamp)) > 0) {
                /*
                 * Spin until it catches the clock back
                 */
            }
            return;
        }

        if (backwardsStamp > brokenThreshold) {
            throw new ClockTooManyBackwardsException(lastTimestamp, System.currentTimeMillis(), brokenThreshold);
        }

        try {
            TimeUnit.MILLISECONDS.sleep(backwardsStamp);
        } catch (InterruptedException interruptedException) {
            log.error("TradeNo waitFor InterruptedException");
            // Restore interrupted state...
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            log.error("TradeNo waitFor InterruptedException e:{}", e);
            throw new SystemException("unknown interrupt");
        }
    }

    private String shapeDataCenterKey() {
        if (dataCenterId >= 90) {
            throw new IllegalArgumentException("Total data center number can't greater than 90");
        }

        if (dataCenterId <= 0) {
            return randomNum(2, 9);
        }
        return StringUtils.leftPad("" + dataCenterId, 2, "0");
    }

    private String shapeLeftPhase() {
        // 兼容老的模式
        if (null != tradePhase) {
            String phaseStr = String.valueOf(tradePhase.getCode());
            int len = phaseStr.length();
            if (len >= 2) {
                throw new IllegalArgumentException("phase can not greater than 99");
            }
            return phaseStr.substring(0, 1);
        }

        if (phase <= 9 || phase > 99) {
            throw new IllegalArgumentException("invalid phase, phase should between 10 and 99");
        }

        return String.valueOf(phase).substring(0, 1);
    }

    private String shapeRightPhase() {
        if (null != tradePhase) {
            return "0";
        }
        return String.valueOf(phase).substring(1);
    }

    /**
     * It's thread-safe so we can share it
     */
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private static String shapeDatetime() {
        return Timestamp.from(Instant.ofEpochSecond(lastTimestamp)).toLocalDateTime().format(formatter);
    }

    private String addMinTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");

        return now.format(formatter);
    }


    private String shapeShardKey() {
        if (shard > 0) {
            return StringUtils.leftPad("" + shard, 4, "0");
        }

        if (accountId < 0) {
            return randomNum(SHARD_LENGTH, 9);
        }

        if (shards <= 0 || shards >= 10000) {
            shards = DEFAULT_SHARDS;
        }

        shard = (int) accountId % shards;
        return StringUtils.leftPad("" + shard, 4, "0");
    }


    private String randomNum(int length) {
        return randomNum(length, 1);
    }

    private String randomNum(int length, int step) {
        if (step < 1) {
            throw new IllegalArgumentException("Random num step can't less than 1: " + step);
        }

        if (length <= 0 || length > 20) {
            throw new IllegalArgumentException("Random num length is invalid:" + length);
        }
        length--;

        int start = (int) Math.pow(10, length) * step;
        int end = (int) Math.pow(10, length + 1d) - 1;

        int key = ThreadLocalRandom.current().nextInt(start, end);
        return String.valueOf(key);
    }
}
