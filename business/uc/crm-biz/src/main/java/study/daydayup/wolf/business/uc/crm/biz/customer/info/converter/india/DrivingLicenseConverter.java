package study.daydayup.wolf.business.uc.crm.biz.customer.info.converter.india;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.uc.api.crm.customer.info.entity.india.DrivingLicense;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.DrivingLicenseDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.info.india.license.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class DrivingLicenseConverter implements Converter {

    public static DrivingLicense toModel(DrivingLicenseDO licenseDO) {
        if (licenseDO == null) {
            return null;
        }

        DrivingLicense license = new DrivingLicense();
        BeanUtils.copyProperties(licenseDO, license);

        return license;
    }

    public static List<DrivingLicense> toModel(List<DrivingLicenseDO> licenseDOList) {
        return CollectionUtil.to(licenseDOList, DrivingLicenseConverter::toModel);
    }

    public static DrivingLicenseDO toDO(DrivingLicense license) {
        if (license == null) {
            return null;
        }

        DrivingLicenseDO licenseDO = new DrivingLicenseDO();
        BeanUtils.copyProperties(license, licenseDO);
        return licenseDO;
    }
}
