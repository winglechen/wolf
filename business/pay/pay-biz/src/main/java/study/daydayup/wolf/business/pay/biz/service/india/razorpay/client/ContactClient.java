package study.daydayup.wolf.business.pay.biz.service.india.razorpay.client;

import com.razorpay.RazorpayException;
import org.json.JSONObject;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.client.core.ApiClient;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.client.core.Constants;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.dto.razor.Contact;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.client
 *
 * @author Wingle
 * @since 2020/3/23 11:33 上午
 **/
public class ContactClient extends ApiClient {
    public ContactClient(String auth) {
        super(auth);
    }

    public Contact create(JSONObject request) throws RazorpayException {
        return post(Constants.CONTACT_CREATE, request);
    }
}
