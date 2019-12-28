package study.daydayup.wolf.business.trade.buy.biz.loan.node;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.entity.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.common.context.BuyContext;
import study.daydayup.wolf.business.trade.buy.biz.common.node.AbstractTradeNode;
import study.daydayup.wolf.common.lang.enums.PeriodStrategyEnum;
import study.daydayup.wolf.common.util.EnumUtil;
import study.daydayup.wolf.common.util.PeriodUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.node
 *
 * @author Wingle
 * @since 2019/12/17 8:19 下午
 **/
@Component
public class FakeLoanEffectTimeNode extends AbstractTradeNode implements TradeNode {
    private PeriodStrategyEnum strategy;

    @Override
    public void run(BuyContext context) {
        init(context);

        List<InstallmentTerm> terms = context.getContract().getInstallmentTermList();
        if (terms == null || terms.isEmpty()) {
            return;
        }

        initPeriodStrategy();
        addEffectTime(terms);
    }

    private void initPeriodStrategy() {
        int code = context.getContract().getLoanTerm().getPeriodStrategy();
        strategy = EnumUtil.codeOf(code, PeriodStrategyEnum.class);
    }

    private void addEffectTime(List<InstallmentTerm> terms) {
        LocalDate start;
        LocalDate end   = PeriodUtil.daysAfter(-1, PeriodStrategyEnum.OPEN_CLOSE);

        for (InstallmentTerm term: terms ) {
            start = PeriodUtil.daysAfter(1, PeriodStrategyEnum.OPEN_CLOSE, end);
            end   = PeriodUtil.daysAfter(term.getPeriod(), strategy, start);

            term.setEffectAt(start);
            term.setDueAt(end);
        }
    }

//    public static void main(String[] args) {
//        List<Integer> arr = Arrays.asList(3, 5, 7, 8);
//
//        LocalDate start;
//        LocalDate end   = PeriodUtil.daysAfter(-1, PeriodStrategyEnum.OPEN_CLOSE);
//
//        for (int i = 0; i < arr.size(); i++) {
//            int no = i + 1;
//            int days = arr.get(i);
//
//            start = PeriodUtil.daysAfter(1, PeriodStrategyEnum.OPEN_CLOSE, end);
//            end   = PeriodUtil.daysAfter(days, PeriodStrategyEnum.CLOSE_CLOSE, start);
//
//            System.out.println("第" + no + "期(" + days +"天)：" +start + " ~ " + end);
//        }
//    }



}
