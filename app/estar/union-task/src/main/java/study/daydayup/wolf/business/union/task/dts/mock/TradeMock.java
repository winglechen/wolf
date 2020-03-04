package study.daydayup.wolf.business.union.task.dts.mock;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.framework.mock.Mock;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.task.dts.mock
 *
 * @author Wingle
 * @since 2020/2/13 1:05 下午
 **/
@Component
public class TradeMock implements Mock {
    @Resource
    private JdbcTemplate jdbc;

    public void run() {
        mockContract();
        mockOrder();
        mockStateLog();
    }

    private void mockContract() {

    }

    private void mockOrder() {

    }

    private void mockStateLog() {

    }
}
