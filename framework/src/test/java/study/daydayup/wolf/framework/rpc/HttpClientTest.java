package study.daydayup.wolf.framework.rpc;

import org.junit.Before;
import org.junit.Test;
import study.daydayup.wolf.framework.rpc.http.client.HttpClient;
import study.daydayup.wolf.framework.rpc.http.client.HttpClientConfig;
import study.daydayup.wolf.framework.rpc.http.client.HttpRequestBuilder;
import study.daydayup.wolf.framework.rpc.http.client.Response;

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
            if(504==response.getCode()){
                // do something
            }
        }
    }
}
