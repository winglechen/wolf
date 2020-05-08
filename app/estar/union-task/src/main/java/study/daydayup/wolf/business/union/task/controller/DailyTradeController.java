package study.daydayup.wolf.business.union.task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.union.task.service.DailyTradeService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.task.controller
 *
 * @author Wingle
 * @since 2020/2/7 4:34 下午
 **/
@RestController
public class DailyTradeController {
    @Resource
    private DailyTradeService tradeService;

    @GetMapping("/daily/trade")
    public String koi() {
        tradeService.countNewContract();
        tradeService.countNewOrder();
        tradeService.countTradeStateChange();

        return "daily trade data creating ...";
    }
}
