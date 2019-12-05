package study.daydayup.wolf.demo.my.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * study.daydayup.wolf.demo.my.starter
 *
 * @author Wingle
 * @since 2019/12/4 8:45 下午
 **/
@Data
//@Configuration
@ConfigurationProperties(prefix = "hello")
public class HelloProperties implements Serializable {
    private String msg = "Hello";
    private boolean show = true;
}
