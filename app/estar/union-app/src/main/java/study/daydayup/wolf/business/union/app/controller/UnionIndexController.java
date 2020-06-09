package study.daydayup.wolf.business.union.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.union.app.service.TradeNoTestService;
import study.daydayup.wolf.framework.rpc.Result;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.union.app.controller
 *
 * @author Wingle
 * @since 2019/12/31 7:17 下午
 **/
@RestController
public class UnionIndexController extends BaseUnionController {
    @Resource
    private TradeNoTestService tradeNoTestService;

    @GetMapping("/union/index")
    public Result<Object> index() {
        return null;
    }


    @GetMapping("/union/tradeNo")
    public String tradeNo() {
        return tradeNoTestService.create();
    }

    public static void main(String[] args) {
        System.out.println("now : " + LocalDateTime.now());
        System.out.println("mill: " + System.currentTimeMillis());
        System.out.println("nano: " + System.nanoTime());
    }
}
