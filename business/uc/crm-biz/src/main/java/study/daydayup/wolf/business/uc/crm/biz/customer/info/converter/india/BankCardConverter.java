package study.daydayup.wolf.business.uc.crm.biz.customer.info.converter.india;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.uc.api.crm.customer.info.entity.india.BankCard;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.BankCardDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.info.india.bankCard.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class BankCardConverter implements Converter {

    public static BankCard toModel(BankCardDO bankCardDO) {
        if (bankCardDO == null) {
            return null;
        }

        BankCard bankCard = new BankCard();
        BeanUtils.copyProperties(bankCardDO, bankCard);

        return bankCard;
    }

    public static List<BankCard> toModel(List<BankCardDO> bankCardDOList) {
        return CollectionUtil.to(bankCardDOList, BankCardConverter::toModel);
    }

    public static BankCardDO toDO(BankCard bankCard) {
        if (bankCard == null) {
            return null;
        }

        BankCardDO bankCardDO = new BankCardDO();
        BeanUtils.copyProperties(bankCard, bankCardDO);
        return bankCardDO;
    }
}
