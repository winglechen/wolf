package study.daydayup.wolf.business.trade.api.vo.buy;

import lombok.Data;
import study.daydayup.wolf.framework.layer.domain.VO;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.api.vo.buy
 *
 * @author Wingle
 * @since 2019/12/13 3:24 下午
 **/
@Data
public class TradeGoods extends VO {
    private long sellId;
    private long goodsId;
    protected long categoryId;
    private String goodsName;
    private int goodsType;
    private int num;

    private long salePrice;

    private int currency;
    private int chargeUnit;

    private String goodsMainPic;
    private int goodsVersion;

    private int giftFlag;
    private long payPrice;
    private long postage;
    private int quantity;

    private TradeSku sku;
    private TradeLoan loan;
    private List<TradeInstallment> installmentList;
}
