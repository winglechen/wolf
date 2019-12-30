package study.daydayup.wolf.business.trade.api.constant;

/**
 * study.daydayup.wolf.business.trade.api.constant
 *
 * @author Wingle
 * @since 2019/12/20 4:31 下午
 **/
public class TradeTag {
    // @range contract + all related orders
    // contract.tag.contains(FIRST_TRADE)
    // + contract.installmentTerm.installmentNo = 1
    // + create repay order
    // = repayOrder.tags.add(FIRST_TRADE)
    public static final String FIRST_TRADE              = "firstTrade";

    public static final String INSTALLMENT_PREFIX       = "installment:";
    public static final String LOAN_EFFECT_PREFIX       = "loanEffectIn:";

    // @range trade state log
    // order.tradeType = repay
    // + order.tags.contains(FIRST_TRADE)
    // + close(expired)
    // = trade.state.log.tag.add(FIRST_OVERDUE)
    public static final String FIRST_OVERDUE            = "firstOverdue";
    // @Range trade state log
    // completed contract(repaidSuccessfully)
    // + createdAt == completedAt
    // = contract.tags.add(REPAY_AT_REQUEST_DAY)
    // = trade.state.log.tag.add(REPAY_AT_REQUEST_DAY)
    public static final String REPAY_AT_REQUEST_DAY     = "repayAtRequestDay";

}
