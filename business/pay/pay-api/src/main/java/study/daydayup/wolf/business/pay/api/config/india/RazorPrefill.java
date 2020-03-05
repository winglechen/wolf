package study.daydayup.wolf.business.pay.api.config.india;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * study.daydayup.wolf.business.pay.api.config.india
 *
 * @author Wingle
 * @since 2020/2/27 4:18 下午
 **/
@Data
public class RazorPrefill implements Serializable {

    private String name = "Gaurav Kumar";
    private String email = "gaurav.kumar@example.com";
    private String contact = "9191919191";

}
