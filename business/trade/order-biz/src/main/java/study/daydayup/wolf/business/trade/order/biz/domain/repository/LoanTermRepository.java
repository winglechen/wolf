package study.daydayup.wolf.business.trade.order.biz.domain.repository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.entity.contract.LoanTerm;
import study.daydayup.wolf.business.trade.order.biz.dal.dao.LoanTermDAO;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.LoanTermDO;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

import javax.annotation.Resource;

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

    public void add(LoanTerm loanTerm) {
        if (loanTerm == null) {
            return;
        }

        LoanTermDO loanTermDO = modelToDO(loanTerm);
        loanTermDAO.insertSelective(loanTermDO);
    }

    public void save(LoanTerm locker, LoanTerm changes) {

    }

    public LoanTerm find(TradeId tradeId) {
        return null;
    }

    private LoanTermDO modelToDO(LoanTerm loanTerm) {
        LoanTermDO loanTermDO = new LoanTermDO();
        BeanUtils.copyProperties(loanTerm, loanTermDO);

        return loanTermDO;
    }

    private LoanTerm DOToModel(LoanTermDO loanTermDO) {
        LoanTerm loanTerm = new LoanTerm();
        BeanUtils.copyProperties(loanTermDO, loanTerm);

        return loanTerm;
    }

}
