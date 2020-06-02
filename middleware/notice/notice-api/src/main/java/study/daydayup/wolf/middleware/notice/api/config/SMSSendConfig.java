package study.daydayup.wolf.middleware.notice.api.config;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Config;

/**
 * study.daydayup.wolf.middleware.notice.api.domain.entity
 *
 * @author Wingle
 * @since 2020/3/20 5:11 下午
 **/
@Data
public class SMSSendConfig implements Config {
    private Long orgId;
    private boolean delay;
    private Integer priority;
}
