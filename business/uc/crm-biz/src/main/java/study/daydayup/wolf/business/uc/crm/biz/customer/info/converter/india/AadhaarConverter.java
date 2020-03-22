package study.daydayup.wolf.business.uc.crm.biz.customer.info.converter.india;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.uc.api.crm.customer.info.entity.india.Aadhaar;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.AadhaarDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.info.india.aadhaar.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class AadhaarConverter implements Converter {

    public static Aadhaar toModel(AadhaarDO aadhaarDO) {
        if (aadhaarDO == null) {
            return null;
        }

        Aadhaar aadhaar = new Aadhaar();
        BeanUtils.copyProperties(aadhaarDO, aadhaar);

        return aadhaar;
    }

    public static List<Aadhaar> toModel(List<AadhaarDO> aadhaarDOList) {
        return CollectionUtil.to(aadhaarDOList, AadhaarConverter::toModel);
    }

    public static AadhaarDO toDO(Aadhaar aadhaar) {
        if (aadhaar == null) {
            return null;
        }

        AadhaarDO aadhaarDO = new AadhaarDO();
        BeanUtils.copyProperties(aadhaar, aadhaarDO);
        return aadhaarDO;
    }
}
