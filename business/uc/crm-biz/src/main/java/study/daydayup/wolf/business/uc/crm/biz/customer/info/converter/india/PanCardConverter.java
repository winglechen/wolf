package study.daydayup.wolf.business.uc.crm.biz.customer.info.converter.india;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.uc.api.crm.customer.info.entity.india.PanCard;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.PanCardDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.info.india.pan.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class PanCardConverter implements Converter {

    public static PanCard toModel(PanCardDO panDO) {
        if (panDO == null) {
            return null;
        }

        PanCard pan = new PanCard();
        BeanUtils.copyProperties(panDO, pan);

        return pan;
    }

    public static List<PanCard> toModel(List<PanCardDO> panDOList) {
        return CollectionUtil.to(panDOList, PanCardConverter::toModel);
    }

    public static PanCardDO toDO(PanCard pan) {
        if (pan == null) {
            return null;
        }

        PanCardDO panDO = new PanCardDO();
        BeanUtils.copyProperties(pan, panDO);
        return panDO;
    }
}
