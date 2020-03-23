package study.daydayup.wolf.business.pay.biz.service.india.razorpay.client.core;

import okhttp3.MediaType;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.client
 *
 * @author Wingle
 * @since 2020/3/23 11:44 上午
 **/
public class Constants {
    // API constants
    public static final String SCHEME = "https";
    public static final String HOSTNAME = "api.razorpay.com";
    public static final Integer PORT = 443;
    public static final String VERSION = "v1";

    public static final String AUTH_HEADER_KEY = "Authorization";
    public static final String USER_AGENT = "User-Agent";
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    public static final String CONTACT_CREATE = "contacts";
    public static final String FUND_ACCOUNT_CREATE = "fund_accounts";
    public static final String PAYOUT_CREATE = "payouts";

}
