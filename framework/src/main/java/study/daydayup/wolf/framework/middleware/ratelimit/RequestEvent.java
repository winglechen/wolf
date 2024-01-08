package study.daydayup.wolf.framework.middleware.ratelimit;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.framework.middleware.ratelimit
 *
 * @author Wingle
 * @since 2020/10/16 2:45 下午
 **/
@Data
@Builder
public class RequestEvent implements Request {
    public static final String PREFIX = "rlt:";

    @NotBlank
    private String key;
    private LocalDateTime requestAt;

    public String getFormattedKey() {
        return StringUtil.join(PREFIX, key);
    }
}
