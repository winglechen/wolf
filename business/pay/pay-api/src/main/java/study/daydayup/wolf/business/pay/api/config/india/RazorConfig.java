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
@Configuration
@ConfigurationProperties(prefix = "pay.razorpay")
public class RazorConfig implements Serializable {

    @NotNull
    private String keyId;
    @NotNull
    private String keySecret;
    @NotNull
    private String webHookSecret;
    @NotNull
    private String keyAccount;

    private String companyName = "Onion Wallet";
    private String companyDescription = "Onion";
    private String companyLogo = "https://onionwallet.net/static/icon_mini.png";

    private RazorPrefill prefill = new RazorPrefill();
    private RazorTheme theme = new RazorTheme();


    //card | netbanking | wallet | emi | upi
    private String prefillMethod = "";
    private String currency = "INR";

}
