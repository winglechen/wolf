package study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLine;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditLineDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class CreditLineConverter implements Converter {

    public static CreditLine toModel(CreditLineDO creditDO) {
        if (creditDO == null) {
            return null;
        }

        CreditLine credit = new CreditLine();
        BeanUtils.copyProperties(creditDO, credit);

        return credit;
    }

    public static List<CreditLine> toModel(List<CreditLineDO> creditDOList) {
        return CollectionUtil.to(creditDOList, CreditLineConverter::toModel);
    }

    public static CreditLineDO toDO(CreditLine credit) {
        if (credit == null) {
            return null;
        }

        CreditLineDO creditDO = new CreditLineDO();
        BeanUtils.copyProperties(credit, creditDO);
        return creditDO;
    }
}
