package study.daydayup.wolf.demo.my.stater.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.util.LocaleUtil;

/**
 * study.daydayup.wolf.demo.my.stater.web.controller
 *
 * @author Wingle
 * @since 2020/2/24 2:32 下午
 **/
@RestController
public class LocaleController {

    @RequestMapping("/locale/hello")
    public String hello() {
        String msg = StringUtil.join(
                "message: ",
                LocaleUtil.get("welcome"),
                " ",
                LocaleUtil.get("name")
        );

        return msg;
    }
}
