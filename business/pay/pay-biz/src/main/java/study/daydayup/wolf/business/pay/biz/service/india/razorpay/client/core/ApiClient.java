package study.daydayup.wolf.business.pay.biz.service.india.razorpay.client.core;

import com.razorpay.RazorpayException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.apache.commons.text.WordUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.dto.razor.Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.client
 *
 * @author Wingle
 * @since 2020/3/23 11:37 上午
 **/
@Slf4j
public class ApiClient {
    String auth;

    private final String ENTITY = "entity";

    private static final String COLLECTION = "collection";
    private static final String ERROR = "error";
    private static final String DESCRIPTION = "description";
    private static final String STATUS_CODE = "code";

    private static final int STATUS_OK = 200;

    private final int STATUS_MULTIPLE_CHOICE = 300;

    public ApiClient(String auth) {
        this.auth = auth;
    }

    public <T extends Entity> T get(String path, JSONObject requestObject) throws RazorpayException {
        Response response = ApiUtils.getRequest(path, requestObject, auth);
        return processResponse(response);
    }

    public <T extends Entity> T post(String path, JSONObject requestObject) throws RazorpayException {
        Response response = ApiUtils.postRequest(path, requestObject, auth);
        return processResponse(response);
    }

    public <T extends Entity> T put(String path, JSONObject requestObject) throws RazorpayException {
        Response response = ApiUtils.putRequest(path, requestObject, auth);
        return processResponse(response);
    }

    public <T extends Entity> T patch(String path, JSONObject requestObject) throws RazorpayException {
        Response response = ApiUtils.patchRequest(path, requestObject, auth);
        return processResponse(response);
    }

    public void delete(String path, JSONObject requestObject) throws RazorpayException {
        Response response = ApiUtils.deleteRequest(path, requestObject, auth);
        processDeleteResponse(response);
    }

    <T extends Entity> ArrayList<T> getCollection(String path, JSONObject requestObject)
            throws RazorpayException {
        Response response = ApiUtils.getRequest(path, requestObject, auth);
        return processCollectionResponse(response);
    }

    private <T extends Entity> T parseResponse(JSONObject jsonObject) throws RazorpayException {
        if (jsonObject.has(ENTITY)) {
            Class<T> cls = getClass(jsonObject.getString(ENTITY));
            try {
                assert cls != null;
                return cls.getConstructor(JSONObject.class).newInstance(jsonObject);
            } catch (Exception e) {
                throw new RazorpayException("Unable to parse response because of " + e.getMessage());
            }
        }

        throw new RazorpayException("Unable to parse response");
    }

    private <T extends Entity> ArrayList<T> parseCollectionResponse(JSONObject jsonObject)
            throws RazorpayException {

        ArrayList<T> modelList = new ArrayList<>();
        if (jsonObject.has(ENTITY) && COLLECTION.equals(jsonObject.getString(ENTITY))) {
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                T t = parseResponse(jsonObj);
                modelList.add(t);
            }
            return modelList;
        }

        throw new RazorpayException("Unable to parse response");
    }

    <T extends Entity> T processResponse(Response response) throws RazorpayException {
        if (response == null) {
            throw new RazorpayException("Invalid Response from server");
        }

        int statusCode = response.code();
        String responseBody;
        JSONObject responseJson;

        try {
            responseBody = Objects.requireNonNull(response.body()).string();
            log.debug("razorpay response:{}", responseBody);

            responseJson = new JSONObject(responseBody);
        } catch (Exception e) {
            throw new RazorpayException(e.getMessage());
        }

        if (statusCode >= STATUS_OK && statusCode < STATUS_MULTIPLE_CHOICE) {
            return parseResponse(responseJson);
        }

        throwException(statusCode, responseJson);
        return null;
    }

    <T extends Entity> ArrayList<T> processCollectionResponse(Response response)
            throws RazorpayException {
        if (response == null) {
            throw new RazorpayException("Invalid Response from server");
        }

        int statusCode = response.code();
        String responseBody;
        JSONObject responseJson;

        try {
            responseBody = Objects.requireNonNull(response.body()).string();
            responseJson = new JSONObject(responseBody);
        } catch (IOException e) {
            throw new RazorpayException(e.getMessage());
        }

        if (statusCode >= STATUS_OK && statusCode < STATUS_MULTIPLE_CHOICE) {
            return parseCollectionResponse(responseJson);
        }

        throwException(statusCode, responseJson);
        return null;
    }

    private void processDeleteResponse(Response response) throws RazorpayException {
        if (response == null) {
            throw new RazorpayException("Invalid Response from server");
        }

        int statusCode = response.code();
        String responseBody;
        JSONObject responseJson;

        try {
            responseBody = Objects.requireNonNull(response.body()).string();
            responseJson = new JSONObject(responseBody);
        } catch (IOException e) {
            throw new RazorpayException(e.getMessage());
        }

        if (statusCode < STATUS_OK || statusCode >= STATUS_MULTIPLE_CHOICE) {
            throwException(statusCode, responseJson);
        }
    }

    private void throwException(int statusCode, JSONObject responseJson) throws RazorpayException {
        if (responseJson.has(ERROR)) {
            JSONObject errorResponse = responseJson.getJSONObject(ERROR);
            String code = errorResponse.getString(STATUS_CODE);
            String description = errorResponse.getString(DESCRIPTION);
            throw new RazorpayException(code + ":" + description);
        }
        throwServerException(statusCode, responseJson.toString());
    }

    private void throwServerException(int statusCode, String responseBody) throws RazorpayException {
        StringBuilder sb = new StringBuilder();
        sb.append("Status Code: ").append(statusCode).append("\n");
        sb.append("Server response: ").append(responseBody);
        throw new RazorpayException(sb.toString());
    }

    @SuppressWarnings("rawtypes")
    private Class getClass(String entity) {
        try {
            String entityClass = "study.daydayup.wolf.business.pay.biz.service.india.razorpay.dto.razor." + WordUtils.capitalize(entity, '_').replaceAll("_", "");
            return Class.forName(entityClass);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
