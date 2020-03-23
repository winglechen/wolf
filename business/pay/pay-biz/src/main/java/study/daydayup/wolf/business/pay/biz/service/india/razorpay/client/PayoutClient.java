package study.daydayup.wolf.business.pay.biz.service.india.razorpay.client;

import com.razorpay.RazorpayException;
import org.json.JSONObject;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.client.core.ApiClient;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.client.core.Constants;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.dto.razor.Payout;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.client
 *
 * @author Wingle
 * @since 2020/3/23 11:33 上午
 **/
public class PayoutClient extends ApiClient {
    public PayoutClient(String auth) {
        super(auth);
    }

    public Payout create(JSONObject request) throws RazorpayException {
        return post(Constants.PAYOUT_CREATE, request);
    }
}
