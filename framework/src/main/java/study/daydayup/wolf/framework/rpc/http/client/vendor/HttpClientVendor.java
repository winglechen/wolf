package study.daydayup.wolf.framework.rpc.http.client.vendor;

import study.daydayup.wolf.framework.rpc.http.client.HttpClientConfig;
import study.daydayup.wolf.framework.rpc.http.client.HttpRequestBuilder;
import study.daydayup.wolf.framework.rpc.http.client.Response;
import study.daydayup.wolf.framework.rpc.http.client.cookie.CookieManager;

public interface HttpClientVendor {
    void setClientConfig(HttpClientConfig config);

    void setCookieManager(CookieManager cookieManager);

    Response execute(HttpRequestBuilder requestBuilder);
}
