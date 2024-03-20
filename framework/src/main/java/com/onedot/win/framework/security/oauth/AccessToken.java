package com.onedot.win.framework.security.oauth;

import lombok.*;
import com.onedot.win.common.lang.ds.ObjectMap;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.framework.layer.api.DTO;

import java.time.LocalDateTime;

/**
 * com.onedot.win.framework.security.oauth
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
