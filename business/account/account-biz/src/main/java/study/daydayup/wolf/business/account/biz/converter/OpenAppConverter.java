package study.daydayup.wolf.business.account.biz.converter;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.account.api.entity.OpenApp;
import study.daydayup.wolf.business.account.biz.dal.dataobject.OpenAppDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class OpenAppConverter implements Converter {
    public static OpenApp toModel(OpenAppDO appDO) {
        if (appDO == null) {
            return null;
        }

        OpenApp credit = new OpenApp();
        BeanUtils.copyProperties(appDO, credit);

        return credit;
    }

    public static List<OpenApp> toModel(List<OpenAppDO> appDOList) {
        return CollectionUtil.to(appDOList, OpenAppConverter::toModel);
    }

    public static OpenAppDO toDO(OpenApp credit) {
        if (credit == null) {
            return null;
        }

        OpenAppDO appDO = new OpenAppDO();
        BeanUtils.copyProperties(credit, appDO);
        return appDO;
    }
}
