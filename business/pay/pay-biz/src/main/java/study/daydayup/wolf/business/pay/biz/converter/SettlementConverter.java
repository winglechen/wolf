package study.daydayup.wolf.business.pay.biz.converter;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.pay.api.domain.entity.Settlement;
import study.daydayup.wolf.business.pay.biz.dal.dataobject.SettlementDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.settlement.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class SettlementConverter implements Converter {
    public static Settlement toModel(SettlementDO settlementDO) {
        if (settlementDO == null) {
            return null;
        }

        Settlement settlement = new Settlement();
        BeanUtils.copyProperties(settlementDO, settlement);

        return settlement;
    }

    public static List<Settlement> toModel(List<SettlementDO> settlementDOList) {
        return CollectionUtil.to(settlementDOList, SettlementConverter::toModel);
    }

    public static SettlementDO toDO(Settlement settlement) {
        if (settlement == null) {
            return null;
        }

        SettlementDO settlementDO = new SettlementDO();
        BeanUtils.copyProperties(settlement, settlementDO);
        return settlementDO;
    }
}
