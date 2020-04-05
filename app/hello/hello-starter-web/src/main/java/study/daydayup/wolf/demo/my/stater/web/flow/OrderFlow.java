package study.daydayup.wolf.demo.my.stater.web.flow;

/**
 * study.daydayup.wolf.demo.my.stater.web.flow
 *
 * @author Wingle
 * @since 2019/12/26 12:15 上午
 **/
public class OrderFlow implements TradeFlow {
    @Override
    public String execute() {
        return "I am order flow";
    }
}
