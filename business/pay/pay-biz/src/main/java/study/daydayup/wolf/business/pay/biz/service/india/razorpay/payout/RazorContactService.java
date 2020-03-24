package study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout;

import com.razorpay.RazorpayException;
import lombok.extern.slf4j.Slf4j;
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
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.layer.domain.Service;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.payout
 *
 * @author Wingle
 * @since 2020/3/23 1:06 上午
 **/
@Component
@Slf4j
public class RazorContactService {
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
        callEpi();

        return this.account;
    }

    private boolean existsContact(RazorAccount account) {
        if (null == account) {
            return false;
        }

        return StringUtil.notEmpty(account.getContactId());
    }

    private void initAccount(RazorAccount razorAccount) {
        if (null == razorAccount) {
            account = new RazorAccount();
        } else {
            account = razorAccount;
        }

        account.setPayeeId(payoutRequest.getPayeeId());
        account.setPayeeName(payoutRequest.getPayeeName());
        account.setPayerId(payoutRequest.getPayerId());
        account.setPayerName(payoutRequest.getPayerName());
    }


    private void callEpi() {
        JSONObject request = createContactRequest();
        log.debug("razorpay contact create request: {}", request);

        try {
            ContactClient client = ClientFactory.createContactClient(config.getKeyId(), config.getKeySecret());
            Contact contact = client.create(request);
            parseResponse(contact);
        } catch (RazorpayException e) {
            throw new PayoutFailException("create razorpay contact fail");
        }
    }

    private JSONObject bakCreateContactRequest() {
        String customerId = CustomerId.toId(payoutRequest.getPayeeId(), payoutRequest.getPayerId());

        ContactEpiRequest request = ContactEpiRequest.builder()
                .name(payoutRequest.getPayerName())
                .type(ContactTypeEnum.CUSTOMER.getName())
                .referenceId(customerId)
                .build();

        return new JSONObject(request);
    }

    private JSONObject createContactRequest() {
        JSONObject request = new JSONObject();
        String customerId = CustomerId.toId(payoutRequest.getPayeeId(), payoutRequest.getPayerId());


//        request.put("name", payoutRequest.getPayerName());
        request.put("name", "onionTest");
        request.put("type", ContactTypeEnum.CUSTOMER.getName());
        request.put("reference_id", customerId);

        return request;
    }

    private void parseResponse(Contact contact) {
        log.debug("razorpay contact create response: {}", contact);
        if (contact == null || null == contact.get("id")) {
            throw new PayoutFailException("create razorpay contact fail");
        }

        account.setContactId(contact.get("id"));
        account.setContactType(contact.get("type"));
        account.setAccountActive(contact.get("active"));
    }
}
