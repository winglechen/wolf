package study.daydayup.wolf.business.pay.biz.service.india.razorpay.model;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.pay.biz.dal.dataobject.RazorpayAccountDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.info.india.account.converter
 *
 * @author Wingle
 * @since 2020/3/10 3:50 下午
 **/
public class RazorAccountConverter implements Converter {

    public static RazorAccount toModel(RazorpayAccountDO accountDO) {
        if (accountDO == null) {
            return null;
        }

        RazorAccount account = new RazorAccount();
        BeanUtils.copyProperties(accountDO, account);

        return account;
    }

    public static List<RazorAccount> toModel(List<RazorpayAccountDO> accountDOList) {
        return CollectionUtil.to(accountDOList, RazorAccountConverter::toModel);
    }

    public static RazorpayAccountDO toDO(RazorAccount account) {
        if (account == null) {
            return null;
        }

        RazorpayAccountDO accountDO = new RazorpayAccountDO();
        BeanUtils.copyProperties(account, accountDO);
        return accountDO;
    }
}
