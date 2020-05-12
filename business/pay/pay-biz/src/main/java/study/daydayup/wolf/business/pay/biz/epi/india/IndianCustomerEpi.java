package study.daydayup.wolf.business.pay.biz.epi.india;

import lombok.NonNull;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.dto.india.IndianBankCard;
import study.daydayup.wolf.business.uc.api.crm.customer.info.dto.india.IndianPayInfo;
import study.daydayup.wolf.business.uc.api.crm.customer.info.service.india.IndianCustomerService;
import study.daydayup.wolf.common.util.lang.BeanUtil;
import study.daydayup.wolf.framework.layer.epi.Epi;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.pay.biz.epi.india
 *
 * @author Wingle
 * @since 2020/3/22 10:08 下午
 **/
@Component
public class IndianCustomerEpi implements Epi {
    @Reference
    private IndianCustomerService customerService;

    public IndianBankCard find(long payerId, long payeeId) {
        if (payeeId <= 0 || payerId <= 0) {
            return null;
        }

        Result<IndianPayInfo> result = customerService.findIndianBankCard(payerId, payeeId);
        if (!result.isSuccess()) {
            return null;
        }

        IndianPayInfo iCard = result.getData();
        return convert(iCard, payerId, payeeId);
    }

    public IndianBankCard findContact(long payerId, long payeeId) {
        if (payeeId <= 0 || payerId <= 0) {
            return null;
        }

        Result<IndianPayInfo> result = customerService.findIndianContact(payerId, payeeId);
        if (!result.isSuccess()) {
            return null;
        }

        IndianPayInfo iCard = result.getData();
        return convert(iCard, payerId, payeeId);
    }

    private IndianBankCard convert(@NonNull IndianPayInfo iCard, long payerId, long payeeId) {
        if (BeanUtil.equals(payerId, iCard.getAccountId())) {
            return null;
        }

        if (BeanUtil.equals(payeeId, iCard.getOrgId())) {
            return null;
        }

        IndianBankCard card = new IndianBankCard();
        BeanUtils.copyProperties(iCard, card);

        card.setPayerId(payerId);
        card.setPayeeId(payeeId);

        return card;
    }
}
