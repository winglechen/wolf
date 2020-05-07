package study.daydayup.wolf.business.uc.setting.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.framework.layer.api.Model;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.uc.setting.api.entity
 *
 * @author Wingle
 * @since 20L20L/1/1 12:25 下午
 **/
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class KvData implements Model {
    public static final String DEFAULT_NAMESPACE = "defNS";
    protected String namespace;
    protected String data;

    protected Integer version;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
