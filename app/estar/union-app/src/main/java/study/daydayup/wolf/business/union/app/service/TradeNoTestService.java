package study.daydayup.wolf.business.union.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.common.model.type.string.id.TradeNo;

import java.util.concurrent.ConcurrentHashMap;

/**
 * study.daydayup.wolf.business.union.app.service
 *
 * @author Wingle
 * @since 2020/6/9 4:35 下午
 **/
@Slf4j
@Service
public class TradeNoTestService {
    private static ConcurrentHashMap<String, Boolean> map = new ConcurrentHashMap<>();

    public String create() {
        String tradeNo = TradeNo.builder()
                .tradePhase(TradePhaseEnum.ORDER_PHASE)
                .accountId(10001)
                .build()
                .create();

        if (null == map.get(tradeNo)) {
            map.put(tradeNo, true);
        } else {
            log.info("tradeNo duplicate: " + tradeNo);
        }

        return tradeNo;
    }

}
