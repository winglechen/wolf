package study.daydayup.wolf.business.pay.biz.service.india.razorpay.dto.razor;

import org.json.JSONObject;

import java.util.Date;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay.dto.razor
 *
 * @author Wingle
 * @since 2020/3/23 12:05 下午
 **/
public class Entity {
    private JSONObject modelJson;

    private final String CREATED_AT = "created_at";
    private final String CAPTURED_AT = "captured_at";

    public Entity(JSONObject jsonObject) {
        this.modelJson = jsonObject;
    }

    public <T> T get(String key) {
        // Return null if key not in JSONObject
        if (!has(key)) {
            return null;
        }
        // Return Date for timestamps
        if (key.equals(CREATED_AT) || key.equals(CAPTURED_AT)) {
            return (T) new Date(modelJson.getLong(key) * 1000);
        }
        Object value = modelJson.get(key);
        if (value == null) {
            return null;
        }
        return (T) value.getClass().cast(value);
    }

    public JSONObject toJson() {
        return modelJson;
    }

    public boolean has(String key) {
        return modelJson.has(key);
    }

    public String toString() {
        return modelJson.toString();
    }
}
