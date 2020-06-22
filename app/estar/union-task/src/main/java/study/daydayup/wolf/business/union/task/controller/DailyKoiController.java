package study.daydayup.wolf.business.union.task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.union.task.service.DailyKoiService;
import study.daydayup.wolf.business.union.task.service.DailySmsService;
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
    private DailyKoiService dailyKoiService;
    @Resource
    private DailyTradeService dailyTradeService;
    @Resource
    private DailySmsService dailySmsService;

    @GetMapping("/daily/koi")
    public String koi() {
        dailyKoiService.countPvAndUv();
        dailyKoiService.countRegister();
        dailyKoiService.countIndianInfoState();

        return "daily koi data creating ...";
    }

    @GetMapping("/daily/koi/audit")
    public String audit() {
        dailyKoiService.countPvAndUv();
        dailyKoiService.countRegister();
        dailyKoiService.countIndianInfoState();

        dailyTradeService.countNewContract();
        dailyTradeService.countNewOrder();
        dailyTradeService.countTradeStateChange();

        dailySmsService.countVerifyCodeSms();

        return "daily audit data creating ...";
    }
}
