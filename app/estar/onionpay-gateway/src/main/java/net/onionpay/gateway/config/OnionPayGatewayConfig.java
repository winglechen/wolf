package net.onionpay.gateway.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * net.onionpay.gateway.config
 *
 * @author Wingle
 * @since 2020/7/6 7:40 下午
 **/
@Configuration
@ConditionalOnWebApplication
@ComponentScan("net.onionpay.gateway")
public class OnionPayGatewayConfig {
}
