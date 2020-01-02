package study.daydayup.wolf.business.trade.api.service.order;

import study.daydayup.wolf.business.trade.api.dto.order.ContractOption;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;

/**
 * study.daydayup.wolf.business.trade.api.service.tm
 *
 * @author Wingle
 * @since 2019/10/9 6:56 下午
 **/
public interface ContractService {
    void create(Contract contract);
    void modify(Contract key, Contract changes);
    Contract find(TradeId tradeId);
    Contract find(TradeId tradeId, ContractOption option);
}
