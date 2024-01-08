package study.daydayup.wolf.framework.util.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class OkHttpUtil {
    public static RequestBody getFormRequestBody(Map<String, Object> args) {
        FormBody.Builder builder = new FormBody.Builder();
        try {
            for (HashMap.Entry<String, Object> entry : args.entrySet()) {
                if (null == entry.getValue()) {
                    continue;
                }

                builder.add(entry.getKey(), entry.getValue().toString());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IllegalArgumentException("invalid request body");
        }
        return builder.build();
    }
}
