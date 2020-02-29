package study.daydayup.wolf.business.pay.api.config.india;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * study.daydayup.wolf.business.pay.api.config.india
 *
 * @author Wingle
 * @since 2020/2/27 4:18 下午
 **/
@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "pay.razorpay")
public class RazorConfig {

    @NotNull
    private String keyId;
    @NotNull
    private String keySecret;
    @NotNull
    private String webHookSecret;

    private String companyName = "";
    private String companyDescription = "";
    private String companyLogo = "";

    private String prefillName = "";
    private String prefillEmail = "";
    private String prefillContact = "";

    private String buttonText = "Pay with Razorpay";
    private String themeColor = "#F37254";

    //card | netbanking | wallet | emi | upi
    private String prefillMethod = "";
    private String currency = "INR";

}
