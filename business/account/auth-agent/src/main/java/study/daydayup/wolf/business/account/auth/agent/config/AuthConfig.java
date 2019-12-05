package study.daydayup.wolf.business.account.auth.agent.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.business.account.auth.agent.config
 *
 * @author Wingle
 * @since 2019/12/5 9:42 上午
 **/
@Data
@ConfigurationProperties(prefix = "wolf.auth")
public class AuthConfig {
    private boolean enable = true;
    private String mode = "exclude"; // include | exclude
    private List<String> paths = new ArrayList<>();

}
