package study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout;

import com.razorpay.RazorpayException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.config.india.RazorConfig;
import study.daydayup.wolf.business.pay.api.domain.exception.InvalidPayoutAccountException;
import study.daydayup.wolf.business.pay.api.domain.exception.PayoutFailException;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.india.BankCard;
import study.daydayup.wolf.business.pay.biz.epi.india.IndianCustomerEpi;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.client.AccountClient;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.client.ClientFactory;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.client.ContactClient;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.dto.razor.Contact;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.dto.razor.FundAccount;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.enums.AccountTypeEnum;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.model.RazorAccount;
import study.daydayup.wolf.framework.layer.domain.Service;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout
 *
 * @author Wingle
 * @since 2020/3/23 1:06 上午
 **/
@Slf4j
@Component
public class RazorFundService implements Service {
    private RazorAccount account;
    private PayoutRequest payoutRequest;
    private BankCard bankCard;

    @Resource
    private RazorConfig config;
    @Resource
    private IndianCustomerEpi customerEpi;

    public RazorAccount create(@NonNull RazorAccount account, @Validated PayoutRequest request) {
        if (validAccount(account)) {
            return account;
        }

        this.account = account;
        this.payoutRequest = request;
        findCustomerInfo();
        callEpi();

        return this.account;
    }

    private boolean validAccount(RazorAccount account) {
        if (null == account || null == account.getPayerId() || null == account.getPayeeId()) {
            throw new InvalidPayoutAccountException("Razorpay payout Can't find customer bankCard info");
        }

        if (null == account.getContactId()) {
            throw new InvalidPayoutAccountException("Razorpay payout Can't find customer contact info");
        }

        return null != account.getAccountId();
    }


    private void findCustomerInfo() {
        bankCard = customerEpi.find(account.getPayerId(), account.getPayeeId());
        if (bankCard == null) {
            throw new InvalidPayoutAccountException("Can't find customer bankCard info");
        }

        account.setAccountName(bankCard.getAadhaarName());
        account.setAccountIfsc(bankCard.getAccountIfsc());
        account.setAccountNumber(bankCard.getAccountNumber());
    }

    private void callEpi() {
        JSONObject request = createRequest();
        log.debug("razorpay contact create request: {}", request);

        try {
            AccountClient client = ClientFactory.createAccountClient(config.getKeyId(), config.getKeySecret());
            FundAccount fundAccount = client.create(request);
            parseResponse(fundAccount);
        } catch (RazorpayException e) {
            throw new PayoutFailException("Razorpay payout create razorpay fund account fail");
        }
    }

    private JSONObject createRequest() {
        JSONObject request = new JSONObject();
        request.put("contact_id", account.getContactId());
        request.put("account_type", AccountTypeEnum.BANK_ACCOUNT.getName());

        JSONObject bankAccount = new JSONObject();
        bankAccount.put("name", account.getAccountName());
        bankAccount.put("ifsc", account.getAccountIfsc());
        bankAccount.put("account_number", account.getAccountNumber());

        request.put("bank_account", bankAccount);
        return request;
    }

    private void parseResponse(FundAccount fund) {
        log.debug("razorpay fund account create response: {}", fund);
        if (fund == null || null == fund.get("id")) {
            throw new PayoutFailException("create razorpay fund account fail");
        }

        account.setAccountType(fund.get("account_type"));
        account.setAccountId(fund.get("id"));
        account.setAccountActive(fund.get("active"));
    }
}
