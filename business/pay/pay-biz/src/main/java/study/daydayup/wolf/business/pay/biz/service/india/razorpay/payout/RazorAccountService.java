package study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.domain.exception.InvalidPayoutAccountException;
import study.daydayup.wolf.business.pay.api.domain.exception.PayoutFailException;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.biz.dal.dao.RazorpayAccountDAO;
import study.daydayup.wolf.business.pay.biz.dal.dataobject.RazorpayAccountDO;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.model.RazorAccount;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.model.RazorAccountConverter;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout
 *
 * @author Wingle
 * @since 2020/3/23 1:06 上午
 **/
@Component
public class RazorAccountService {
    private PayoutRequest request;
    private RazorAccount account;

    @Resource
    private RazorpayAccountDAO razorpayAccountDAO;
    @Resource
    private RazorContactService contactService;
    @Resource
    private RazorFundService fundService;

    public RazorAccount find(@Validated PayoutRequest request) {
        this.request = request;

        findFromDb();
        createContact();
        creatFundAccount();

        return account;
    }

    private void findFromDb() {
        RazorpayAccountDO accountDO = razorpayAccountDAO.selectByPayerId(request.getPayerId(), request.getPayeeId());
        if (accountDO == null) {
            return;
        }

        account = RazorAccountConverter.toModel(accountDO);
    }

    private void createContact() {
        if (null != account && StringUtil.notEmpty(account.getContactId(), true)) {
            return;
        }

        account = contactService.create(account, request);
        if (account == null) {
            throw new PayoutFailException("razorpay payout create contact fail");
        }

        saveAccountToDb();
    }

    private void creatFundAccount() {
        if (null == account || StringUtil.isEmpty(account.getContactId(), true)) {
            throw new InvalidPayoutAccountException("razorpay contact can't be null");
        }
        account = fundService.create(account, request);

        saveAccountToDb();
    }

    private void saveAccountToDb() {
        if (account == null) {
            return;
        }

        if (null == account.getId() || account.getId() <= 0) {
            addToDb();
            return;
        }

        modifyToDb();
    }

    private void addToDb() {
        if (account == null) {
            return;
        }

        RazorpayAccountDO accountDO = RazorAccountConverter.toDO(account);
        if (accountDO == null) {
            return;
        }

        razorpayAccountDAO.insertSelective(accountDO);
        if (null != accountDO.getId()) {
            account.setId(accountDO.getId());
        }
    }

    private void modifyToDb() {
        if (account == null || null == account.getId()) {
            return;
        }

        RazorpayAccountDO accountDO = RazorAccountConverter.toDO(account);
        if (accountDO == null) {
            return;
        }

        razorpayAccountDAO.updateByPayerId(accountDO, accountDO.getPayerId(), accountDO.getPayeeId());
    }

}
