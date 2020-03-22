package study.daydayup.wolf.business.uc.crm.biz.customer.info.converter.india;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.uc.api.crm.customer.info.entity.india.VoterCard;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.VoterCardDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.info.india.voter.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class VoterCardConverter implements Converter {

    public static VoterCard toModel(VoterCardDO voterDO) {
        if (voterDO == null) {
            return null;
        }

        VoterCard voter = new VoterCard();
        BeanUtils.copyProperties(voterDO, voter);

        return voter;
    }

    public static List<VoterCard> toModel(List<VoterCardDO> voterDOList) {
        return CollectionUtil.to(voterDOList, VoterCardConverter::toModel);
    }

    public static VoterCardDO toDO(VoterCard voter) {
        if (voter == null) {
            return null;
        }

        VoterCardDO voterDO = new VoterCardDO();
        BeanUtils.copyProperties(voter, voterDO);
        return voterDO;
    }
}
