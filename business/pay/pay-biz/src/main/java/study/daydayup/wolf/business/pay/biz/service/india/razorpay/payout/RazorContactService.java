package study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout;

import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.config.india.RazorConfig;
import study.daydayup.wolf.business.pay.api.domain.exception.PayoutFailException;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.client.ClientFactory;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.client.ContactClient;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.dto.ContactEpiRequest;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.dto.razor.Contact;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.enums.ContactTypeEnum;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.model.RazorAccount;
import study.daydayup.wolf.business.uc.api.crm.customer.info.dto.CustomerId;
import study.daydayup.wolf.framework.layer.domain.Service;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout
 *
 * @author Wingle
 * @since 2020/3/23 1:06 上午
 **/
@Component
public class RazorContactService implements Service {
    private RazorAccount account;
    private PayoutRequest payoutRequest;

    @Resource
    private RazorConfig config;

    public RazorAccount create(RazorAccount account, @Validated PayoutRequest request) {
        if (existsContact(account)) {
            return account;
        }

        payoutRequest = request;
        initAccount(account);

        return this.account;
    }

    private boolean existsContact(RazorAccount account) {
        if (null == account) {
            return false;
        }

        return null != account.getContactId();
    }

    private void initAccount(RazorAccount account) {
        if (null == account) {
            this.account = new RazorAccount();
        } else {
            this.account = account;
        }
    }

    private JSONObject createContactRequest() {
        String customerId = CustomerId.toId(payoutRequest.getPayeeId(), payoutRequest.getPayerId());

        ContactEpiRequest request = ContactEpiRequest.builder()
                .name(payoutRequest.getPayerName())
                .type(ContactTypeEnum.CUSTOMER.getName())
                .referenceId(customerId)
                .build();

        return new JSONObject(request);
    }

    private void callEpi() {
        JSONObject request = createContactRequest();

        try {
            ContactClient client = ClientFactory.createContactClient(config.getKeyId(), config.getKeySecret());
            Contact contact = client.create(request);
            parseResponse(contact);
        } catch (RazorpayException e) {
            account = null;
            throw new PayoutFailException("create razorpay contact fail");
        }
    }

    private void parseResponse(Contact contact) {

    }
}
