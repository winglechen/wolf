package study.daydayup.wolf.business.trade.buy.biz.loan.node;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.buy.biz.base.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.base.context.BuyContext;
import study.daydayup.wolf.business.trade.buy.biz.base.node.AbstractTradeNode;
import study.daydayup.wolf.common.lang.enums.PeriodStrategyEnum;
import study.daydayup.wolf.common.util.lang.EnumUtil;
import study.daydayup.wolf.common.util.time.PeriodUtil;

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

}
