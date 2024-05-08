package com.wolf.framework.rpc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.wolf.framework.rpc.http.client.HttpClient;
import com.wolf.framework.rpc.http.client.HttpClientConfig;
import com.wolf.framework.rpc.http.client.HttpRequestBuilder;
import com.wolf.framework.rpc.http.client.Response;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: TestCase for HttpClient(Only Netty)
 * @author: yik
 * @version: V1.0
 */
public class HttpClientTest {

    private final static String url = "https://virtserver.swaggerhub.com/ERICGYG/iot/1.0.0/devices";

    //@Test
    public void test_one_thread_request_twice_should_ok() {
        HttpClientConfig config = new HttpClientConfig();
        HttpClient.init(config);
        Map params = new HashMap<>();
        params.put("skip", 1);
        params.put("limit", 10);

        HttpRequestBuilder requestBuilder = HttpClient.builder()
            //.header("accept", "*/*")
            .header("a", "b").get(url, params);
        //.url(url);

        HttpClient.execute(requestBuilder);

        HttpRequestBuilder requestBuilder2 = HttpClient.builder().get("https://www.baidu.com/", params).header("accept", "application/json");
        //.url("");

        HttpClient.execute(requestBuilder2);
    }

    @Before
    public void before() {
        HttpClientConfig config = new HttpClientConfig();
        HttpClient.init(config);
    }

    @Test
    public void test_on_response_timeout() {
        int serverSleepInMillis = 3000;
        String host = "https://httpstat.us/200?sleep=" + serverSleepInMillis;
        HttpRequestBuilder timeoutRequestBuilder = HttpClient.builder().timeout(Duration.ofMillis(serverSleepInMillis - 1000)).get(host);
        //call execute() never throw exception
        Response response = HttpClient.execute(timeoutRequestBuilder);
        if (response.isSuccess()) {
            Assert.fail("Should timeout");
        } else {
            System.out.println(response);
        }
    }

    @Test
    public void test_on_response_not_timeout() {
        int serverSleepInMillis = 200;
        String host = "https://httpstat.us/200?sleep=" + serverSleepInMillis;
        HttpRequestBuilder timeoutRequestBuilder = HttpClient.builder().timeout(Duration.ofMillis(serverSleepInMillis + 1000)).get(host);
        //call execute() never throw exception
        Response response = HttpClient.execute(timeoutRequestBuilder);
        if (response.isSuccess()) {
            System.out.println(response);
        } else {
            Assert.fail("Should not timeout");
        }
    }


    //    @Test
    public void test_on_connection_timeout() {
        String lostConnectionHost = "https://www.google.com/";
        HttpRequestBuilder requestBuilder = HttpClient.builder().get(lostConnectionHost);

        //call execute() never throw exception
        Response response = HttpClient.execute(requestBuilder);
        if (response.isSuccess()) {
            System.out.println("OK");
        } else {
            //do your business fallback here
            System.out.println(response.getErrorMessage());
            //or by specific error code
            if (504 == response.getCode()) {
                // do something
            }
        }
    }
}
