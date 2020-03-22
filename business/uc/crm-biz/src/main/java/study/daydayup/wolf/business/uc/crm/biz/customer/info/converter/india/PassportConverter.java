package study.daydayup.wolf.business.uc.crm.biz.customer.info.converter.india;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.uc.api.crm.customer.info.entity.india.Passport;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.PassportDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.info.india.passport.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class PassportConverter implements Converter {

    public static Passport toModel(PassportDO passportDO) {
        if (passportDO == null) {
            return null;
        }

        Passport passport = new Passport();
        BeanUtils.copyProperties(passportDO, passport);

        return passport;
    }

    public static List<Passport> toModel(List<PassportDO> passportDOList) {
        return CollectionUtil.to(passportDOList, PassportConverter::toModel);
    }

    public static PassportDO toDO(Passport passport) {
        if (passport == null) {
            return null;
        }

        PassportDO passportDO = new PassportDO();
        BeanUtils.copyProperties(passport, passportDO);
        return passportDO;
    }
}
