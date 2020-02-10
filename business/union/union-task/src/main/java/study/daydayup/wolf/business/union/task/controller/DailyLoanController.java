package study.daydayup.wolf.business.union.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.union.task.service.DailyLoanService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.task.controller
 *
 * @author Wingle
 * @since 2020/2/7 4:35 下午
 **/
@RestController
public class DailyLoanController {
    @Resource
    private DailyLoanService loanService;

    @RequestMapping("/daily/loan")
    public String loan() {
        loanService.countLoanContract();
        loanService.countLoanContractState();
        loanService.countLoanOrder();
        loanService.countRepayContract();
        loanService.countRepayOrder();

        return "daily loan executing ....";
    }
}
