package study.daydayup.wolf.business.pay.api.dto;

import lombok.NonNull;

import java.util.Map;

/**
 * study.daydayup.wolf.business.pay.api.dto
 *
 * @author Wingle
 * @since 2020/2/27 4:50 下午
 **/
public class Attachment {
    protected Map<String, Object> attachment;

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public Object get(@NonNull String key) {
        return attachment.get(key);
    }

    public void set(@NonNull String key, Object value) {
        attachment.put(key, value);
    }
}
