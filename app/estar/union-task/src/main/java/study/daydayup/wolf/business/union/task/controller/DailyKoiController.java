package study.daydayup.wolf.business.union.task.controller;

import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.union.task.service.DailyKoiService;

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

    public String koi() {
        koiService.countPvAndUv();
        koiService.countRegister();
        koiService.countIndianInfoState();
        
        return "daily koi data creating ...";
    }
}
