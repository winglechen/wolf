package study.daydayup.wolf.demo.my.stater.web.flow;

import org.springframework.stereotype.Component;

/**
 * study.daydayup.wolf.demo.my.stater.web.flow
 *
 * @author Wingle
 * @since 2019/12/26 12:15 上午
 **/
public class ContractFlow implements TradeFlow {
    @Override
    public String execute() {
        return "I am contract flow";
    }
}
