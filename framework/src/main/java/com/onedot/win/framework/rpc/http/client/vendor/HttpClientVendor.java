package com.onedot.win.framework.rpc.http.client.vendor;

import com.onedot.win.framework.rpc.http.client.HttpClientConfig;
import com.onedot.win.framework.rpc.http.client.HttpRequestBuilder;
import com.onedot.win.framework.rpc.http.client.Response;
import com.onedot.win.framework.rpc.http.client.cookie.CookieManager;

public interface HttpClientVendor {
    void setClientConfig(HttpClientConfig config);

    void setCookieManager(CookieManager cookieManager);

    Response execute(HttpRequestBuilder requestBuilder);
}
