package study.daydayup.wolf.business.union.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.union.task.dts.mock.TradeMock;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.task.controller
 *
 * @author Wingle
 * @since 2020/2/13 1:04 下午
 **/
@RestController
public class MockController {
    @Resource
    private TradeMock tradeMock;

    @RequestMapping("/mock/all")
    public String mock() {
        tradeMock.run();

        return "mocking ...";
    }
}
