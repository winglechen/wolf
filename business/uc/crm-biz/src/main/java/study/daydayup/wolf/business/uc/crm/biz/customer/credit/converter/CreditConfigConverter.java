package study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditConfigDO;
import study.daydayup.wolf.framework.layer.converter.Converter;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class CreditConfigConverter implements Converter {

    public static CreditConfig toModel(CreditConfigDO configDO) {
        if (configDO == null) {
            return null;
        }

        CreditConfig config = new CreditConfig();
        BeanUtils.copyProperties(configDO, config);

        return config;
    }

    public static CreditConfigDO toDO(CreditConfig config) {
        if (config == null) {
            return null;
        }

        CreditConfigDO configDO = new CreditConfigDO();
        BeanUtils.copyProperties(config, configDO);
        return configDO;
    }
}
