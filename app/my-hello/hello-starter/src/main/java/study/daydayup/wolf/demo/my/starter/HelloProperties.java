package study.daydayup.wolf.demo.my.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * study.daydayup.wolf.demo.my.starter
 *
 * @author Wingle
 * @since 2019/12/4 8:45 下午
 **/
@Data
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {
    private String msg = "Hello";
    private boolean show = true;
}
