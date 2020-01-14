package study.daydayup.wolf.business.trade.api.config;

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

    public static final String INSTALLMENT_PREFIX       = "ist";
    public static final String LOAN_EFFECT_PREFIX       = "lei";

    public static final String PHONE_PREFIX             = "pho";
    public static final String SELLER_ID_PREFIX         = "sid";
    public static final String BUYER_ID_PREFIX          = "bid";
    public static final String TRADE_TYPE_PREFIX        = "typ";
    public static final String CREATE_DAY_PREFIX        = "cdy";



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
