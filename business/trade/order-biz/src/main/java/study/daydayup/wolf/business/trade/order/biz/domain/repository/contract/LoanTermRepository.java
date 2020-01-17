package study.daydayup.wolf.business.trade.order.biz.domain.repository.contract;

import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.LoanTerm;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.TradeIds;
import study.daydayup.wolf.business.trade.order.biz.converter.LoanTermConverter;
import study.daydayup.wolf.business.trade.order.biz.dal.dao.LoanTermDAO;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.LoanTermDO;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository
 *
 * @author Wingle
 * @since 2019/12/26 9:06 下午
 **/
@Component
public class LoanTermRepository extends AbstractRepository implements Repository {
    @Resource
    private LoanTermDAO loanTermDAO;
    @Resource
    private LoanTermConverter converter;

    public void add(@NonNull LoanTerm loanTerm) {
        if (loanTerm == null) {
            return;
        }

        LoanTermDO loanTermDO = converter.toDo(loanTerm);
        if (loanTermDO == null) {
            return;
        }

        loanTermDAO.insertSelective(loanTermDO);
    }

    public void save(LoanTerm key, LoanTerm changes) {

    }

    public LoanTerm find(@NonNull TradeId tradeId) {
        tradeId.valid();

        LoanTermDO loanTermDO = loanTermDAO.selectByTradeNo(
                tradeId.getTradeNo(), tradeId.getBuyerId(), tradeId.getSellerId()
        );
        return converter.toModel(loanTermDO);
    }

    public List<LoanTerm> find(@NonNull TradeIds tradeIds) {
        tradeIds.valid();

        List<LoanTermDO> loanTermDOList = loanTermDAO.selectByTradeNoIn(
                tradeIds.getTradeNoSet(), tradeIds.getBuyerId(), tradeIds.getSellerId()
        );

        return converter.toModel(loanTermDOList);
    }

}
