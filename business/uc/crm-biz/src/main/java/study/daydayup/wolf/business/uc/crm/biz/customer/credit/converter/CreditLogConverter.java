package study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLog;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditLogDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class CreditLogConverter implements Converter {
    public static CreditLogDO toDO(CreditLog log) {
        if (log == null) {
            return null;
        }

        CreditLogDO logDO = new CreditLogDO();
        BeanUtils.copyProperties(log, logDO);
        return logDO;
    }

    public static CreditLog toModel(CreditLogDO logDO) {
        if (logDO == null) {
            return null;
        }

        CreditLog log = new CreditLog();
        BeanUtils.copyProperties(logDO, log);

        return log;
    }

    public static List<CreditLog> toModel(List<CreditLogDO> logDOList) {
        return CollectionUtil.to(logDOList, CreditLogConverter::toModel);
    }

}
