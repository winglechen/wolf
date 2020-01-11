package study.daydayup.wolf.business.goods.biz.loan;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.goods.api.entity.goods.LoanGoods;
import study.daydayup.wolf.business.goods.api.vo.Installment;
import study.daydayup.wolf.business.goods.api.vo.Loan;
import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsDO;
import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsLoanDO;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.goods.biz.loan
 *
 * @author Wingle
 * @since 2020/1/11 2:32 下午
 **/
@Component
public class LoanConverter implements Converter {
    //TODO:  change price to double
    public LoanEntity toEntity(GoodsDO goodsDO) {
        if (goodsDO == null) {
            return null;
        }
        LoanEntity entity = new LoanEntity();
        BeanUtils.copyProperties(goodsDO, entity);
        entity.setPrice(goodsDO.getPrice() / 10000);

        return entity;
    }

    //TODO:  change price to double
    public GoodsDO toDo(LoanEntity entity) {
        if (entity == null) {
            return null;
        }
        GoodsDO goodsDO = new GoodsDO();
        BeanUtils.copyProperties(entity, goodsDO);
        goodsDO.setPrice(10000 * entity.getPrice());

        return goodsDO;
    }

    public LoanEntity toEntity(GoodsLoanDO loanDO, LoanEntity entity) {
        if (loanDO == null) {
            return entity;
        }

        Loan loan = new Loan();
        BeanUtils.copyProperties(loanDO, loan);
        entity.setLoan(loan);

        List<Installment> installmentList = JSON.parseArray(loanDO.getInstallment(), Installment.class);
        entity.setInstallmentList(installmentList);

        return entity;
    }

    public GoodsLoanDO toDo(Loan loan, List<Installment> installmentList) {
        if (loan == null) {
            return null;
        }

        GoodsLoanDO loanDO = new GoodsLoanDO();
        BeanUtils.copyProperties(loan, loanDO);

        loanDO.setId(null);
        String installments = JSON.toJSONString(installmentList);
        loanDO.setInstallment(installments);

        return loanDO;
    }

    public LoanGoods toModel(LoanEntity entity) {
        return entity;
    }
}
