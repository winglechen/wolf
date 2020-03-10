package study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLine;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditLineDO;
import study.daydayup.wolf.framework.layer.converter.Converter;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class CreditLineConverter implements Converter {

    public static CreditLine toModel(CreditLineDO configDO) {
        if (configDO == null) {
            return null;
        }

        CreditLine config = new CreditLine();
        BeanUtils.copyProperties(configDO, config);

        return config;
    }

    public static CreditLineDO toDO(CreditLine config) {
        if (config == null) {
            return null;
        }

        CreditLineDO configDO = new CreditLineDO();
        BeanUtils.copyProperties(config, configDO);
        return configDO;
    }
}
