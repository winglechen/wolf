package study.daydayup.wolf.business.uc.api.setting.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.uc.api.setting.entity
 *
 * @author Wingle
 * @since 20L20L/1/1 12:25 下午
 **/
@Data
public class KvData implements Model {
    public static final String DEFAULT_NAMESPACE = "defNS";
    protected String namespace = DEFAULT_NAMESPACE;
    protected String data;

    protected Integer version;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
