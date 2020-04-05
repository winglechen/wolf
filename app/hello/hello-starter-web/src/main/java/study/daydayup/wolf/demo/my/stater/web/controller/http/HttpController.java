package study.daydayup.wolf.demo.my.stater.web.controller.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * study.daydayup.wolf.demo.my.stater.web.controller.http
 *
 * @author Wingle
 * @since 2020/3/27 8:23 下午
 **/
@RestController
public class HttpController {
    @GetMapping("/http/auth")
    public String auth(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");

        String[] args = auth.split(" ");
        String authType = args[0];
        String authData = args[1];


        return StringUtil.joinWith(":", authType, authData);
    }
}
