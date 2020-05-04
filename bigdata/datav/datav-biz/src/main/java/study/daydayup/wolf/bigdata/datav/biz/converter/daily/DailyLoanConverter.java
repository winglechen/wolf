package study.daydayup.wolf.bigdata.datav.biz.converter.daily;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.bigdata.datav.api.entity.daily.DailyLoan;
import study.daydayup.wolf.bigdata.datav.biz.dal.dataobject.DailyLoanDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.bigdata.datav.biz.converter.daily
 *
 * @author Wingle
 * @since 2020/3/26 12:24 上午
 **/
public class DailyLoanConverter implements Converter {
    public static DailyLoanDO toDO(DailyLoan loan) {
        if (loan == null) {
            return null;
        }

        DailyLoanDO loanDO = new DailyLoanDO();
        BeanUtils.copyProperties(loan, loanDO);
        return loanDO;
    }

    public static DailyLoan toModel(DailyLoanDO loanDO) {
        if (loanDO == null) {
            return null;
        }

        DailyLoan loan = new DailyLoan();
        BeanUtils.copyProperties(loanDO, loan);

        return loan;
    }

    public static List<DailyLoan> toModel(List<DailyLoanDO> loanDOList) {
        return CollectionUtil.to(loanDOList, DailyLoanConverter::toModel);
    }

}
