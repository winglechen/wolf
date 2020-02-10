package study.daydayup.wolf.business.trade.order.biz.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.LoanTerm;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.LoanTermDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.biz.converter
 *
 * @author Wingle
 * @since 2020/1/17 10:07 下午
 **/
@Component
public class LoanTermConverter implements Converter {
    public LoanTermDO toDo(LoanTerm loanTerm) {
        if (loanTerm == null) {
            return null;
        }

        LoanTermDO loanTermDO = new LoanTermDO();
        BeanUtils.copyProperties(loanTerm, loanTermDO);

        return loanTermDO;
    }

    public LoanTerm toModel(LoanTermDO loanTermDO) {
        if (loanTermDO == null) {
            return null;
        }

        LoanTerm loanTerm = new LoanTerm();
        BeanUtils.copyProperties(loanTermDO, loanTerm);

        return loanTerm;
    }

    public List<LoanTerm> toModel(List<LoanTermDO> loanTermDOList) {
        return CollectionUtil.to(loanTermDOList, this::toModel);
    }
}
