package com.wolf.framework.rpc.http.client.vendor;

import com.wolf.framework.rpc.http.client.HttpClientConfig;
import com.wolf.framework.rpc.http.client.HttpRequestBuilder;
import com.wolf.framework.rpc.http.client.Response;
import com.wolf.framework.rpc.http.client.cookie.CookieManager;

public interface HttpClientVendor {
    void setClientConfig(HttpClientConfig config);

    void setCookieManager(CookieManager cookieManager);

    Response execute(HttpRequestBuilder requestBuilder);
}
