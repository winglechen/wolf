package net.onionpay.gateway.pg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * net.onionpay.gateway.pg.controller
 *
 * @author Wingle
 * @since 2020/7/6 8:23 下午
 **/
@RestController
public class PayDemoController {

    @GetMapping("/wingle/demo")
    public String demo() {
        return "hello pg";
    }
}
