package study.daydayup.wolf.business.union.task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.union.task.service.DailyKoiService;
import study.daydayup.wolf.business.union.task.service.DailyTradeService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.task.controller
 *
 * @author Wingle
 * @since 2020/2/7 4:34 下午
 **/
@RestController
public class DailyKoiController {
    @Resource
    private DailyKoiService koiService;
    @Resource
    private DailyTradeService tradeService;

    @GetMapping("/daily/koi")
    public String koi() {
        koiService.countPvAndUv();
        koiService.countRegister();
        koiService.countIndianInfoState();

        return "daily koi data creating ...";
    }

    @GetMapping("/daily/koi/audit")
    public String audit() {
        koiService.countPvAndUv();
        koiService.countRegister();
        koiService.countIndianInfoState();

        tradeService.countNewContract();
        tradeService.countNewOrder();
        tradeService.countTradeStateChange();

        return "daily audit data creating ...";
    }
}
