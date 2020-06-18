package study.daydayup.wolf.common.model.type.string.id;

import org.junit.Test;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.common.model.type.string.id
 *
 * @author Wingle
 * @since 2020/5/25 9:28 上午
 **/
public class TradeNoTest {

//    @Test
    public void recreate() {
        String tradeNo = createTradeNo();
        String newTradeNo = TradeNo.recreate(tradeNo);

        System.out.println(tradeNo);
        System.out.println(newTradeNo);
    }
//    @Test
    public void create() {
        create2();
//        create3();
//        create4();
    }

//    @Test
    public void demo() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("i: " + i + "; ms: " + System.currentTimeMillis());
            System.out.println("tradeNo: " + createTradeNo());
//            System.out.println("ms: " + System.nanoTime());
        }
    }

    public void create2() {
        Map<String, Boolean> map = new HashMap<>();

        String tradeNo;
        int dupCount = 0;
        for (int i = 0; i < 1000000; i++) {
            tradeNo = createTradeNo();

            if (null == map.get(tradeNo)) {
                map.put(tradeNo, true);
            } else {
                dupCount++;
            }
        }

        System.out.println("random count:" + dupCount);
    }

    public void create3() {
        Map<String, Boolean> map = new HashMap<>();

        String tradeNo;
        int dupCount = 0;
        for (int i = 0; i < 1000000; i++) {
            tradeNo = createTradeNo3();

            if (null == map.get(tradeNo)) {
                map.put(tradeNo, true);
            } else {
                dupCount++;
            }
        }

        System.out.println("secure count:" + dupCount);
    }

    public void create4() {
        Map<String, Boolean> map = new HashMap<>();

        String tradeNo;
        int dupCount = 0;
        for (int i = 0; i < 1000000; i++) {
            tradeNo = createTradeNo4();

            if (null == map.get(tradeNo)) {
                map.put(tradeNo, true);
            } else {
                dupCount++;
            }
        }

        System.out.println("secure count:" + dupCount);
    }

    private String createTradeNo1() {
        int n = ThreadLocalRandom.current().nextInt(1000000);

        return String.valueOf(n);
    }

    private String createTradeNo2() {
        Random random = new Random();
        return String.valueOf(random.nextInt(1000000));
    }

    private String createTradeNo3() {
        SecureRandom random = new SecureRandom();
        return String.valueOf(random.nextInt(1000000));
    }

    private String createTradeNo4() {
        UUID uuid = UUID.randomUUID();
        int hashCode = uuid.hashCode();
        if (hashCode < 0) {
            hashCode = - hashCode;
        }

        String s = String.format("%06d", hashCode);
        return s.substring(0, 6);
    }

    private String createTradeNo() {
        return TradeNo.builder()
                .tradePhase(TradePhaseEnum.ORDER_PHASE)
                .accountId(10001)
                .build()
                .create();
    }
}