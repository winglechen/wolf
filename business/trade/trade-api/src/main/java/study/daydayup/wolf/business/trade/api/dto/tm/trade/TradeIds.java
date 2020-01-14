package study.daydayup.wolf.business.trade.api.dto.tm.trade;

import lombok.Data;
import lombok.NonNull;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.TradeOwner;
import study.daydayup.wolf.framework.layer.api.Request;

import java.util.Set;
import java.util.TreeSet;

/**
 * study.daydayup.wolf.business.trade.api.dto.tm
 *
 * @author Wingle
 * @since 2020/1/10 4:35 下午
 **/
@Data
public class TradeIds extends TradeOwner implements Request {
    private Set<String> tradeNoSet = new TreeSet<>();

    public void add(@NonNull String tradeNo) {
        tradeNoSet.add(tradeNo);
    }
}
