package study.daydayup.wolf.framework.security.oauth;

import lombok.*;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.layer.api.DTO;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.framework.security.oauth
 *
 * @author Wingle
 * @since 2021/3/9 5:17 下午
 **/
@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessToken extends ObjectMap implements DTO {
    private String clientId;
    private String clientSecret;

    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private String scope;

    private Integer expireIn;
    private LocalDateTime createdAt;

    public boolean isRefreshable() {
        if (StringUtil.isBlank(refreshToken)) {
            return false;
        }

        if (null == expireIn) {
            return false;
        }

        return expireIn >= 0;
    }

}
