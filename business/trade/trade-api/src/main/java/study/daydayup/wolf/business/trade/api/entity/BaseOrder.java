package study.daydayup.wolf.business.trade.api.entity;

import lombok.Data;

/**
 * study.daydayup.wolf.business.trade.api.entity
 *
 * @author Wingle
 * @since 2019/10/4 12:04 AM
 **/
@Data
public class BaseOrder extends OrderState {
    private long buyerId;
    private String buyerName;

    private long sellerId;
    private String sellerName;

    private int totalAmount;
    private int postage;
    private int paymentMethod;

    private String payNo;
    private String outerTransactionNo;
}
