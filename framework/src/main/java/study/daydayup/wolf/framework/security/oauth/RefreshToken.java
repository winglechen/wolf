package study.daydayup.wolf.framework.security.oauth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.framework.layer.api.DTO;

/**
 * study.daydayup.wolf.framework.security.oauth
 *
 * @author Wingle
 * @since 2021/3/9 5:20 下午
 **/
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken extends ObjectMap implements DTO {
    private String clientId;
    private String clientSecret;

    private String refreshToken;
    private String grantType;
    private String scope;

}
