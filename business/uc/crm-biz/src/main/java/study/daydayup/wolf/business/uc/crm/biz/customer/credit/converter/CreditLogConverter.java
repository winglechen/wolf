package study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLog;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditLogDO;
import study.daydayup.wolf.framework.layer.converter.Converter;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class CreditLogConverter implements Converter {

    public static CreditLog toModel(CreditLogDO configDO) {
        if (configDO == null) {
            return null;
        }

        CreditLog config = new CreditLog();
        BeanUtils.copyProperties(configDO, config);

        return config;
    }

    public static CreditLogDO toDO(CreditLog config) {
        if (config == null) {
            return null;
        }

        CreditLogDO configDO = new CreditLogDO();
        BeanUtils.copyProperties(config, configDO);
        return configDO;
    }
}
