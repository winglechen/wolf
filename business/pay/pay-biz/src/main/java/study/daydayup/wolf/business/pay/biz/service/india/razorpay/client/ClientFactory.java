package study.daydayup.wolf.business.pay.biz.service.india.razorpay.client;

import com.razorpay.RazorpayException;
import okhttp3.Credentials;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.client.core.ApiUtils;
import study.daydayup.wolf.framework.layer.domain.Factory;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.client
 *
 * @author Wingle
 * @since 2020/3/23 11:31 上午
 **/
public class ClientFactory implements Factory {
    public static AccountClient createAccountClient(String key, String secret) throws RazorpayException {
        ApiUtils.createHttpClientInstance(false);
        String auth = Credentials.basic(key, secret);

        return new AccountClient(auth);
    }

    public static ContactClient createContactClient(String key, String secret) throws RazorpayException {
        ApiUtils.createHttpClientInstance(false);
        String auth = Credentials.basic(key, secret);

        return new ContactClient(auth);
    }

    public static PayoutClient createPayoutClient(String key, String secret) throws RazorpayException {
        ApiUtils.createHttpClientInstance(false);
        String auth = Credentials.basic(key, secret);

        return new PayoutClient(auth);
    }
}
