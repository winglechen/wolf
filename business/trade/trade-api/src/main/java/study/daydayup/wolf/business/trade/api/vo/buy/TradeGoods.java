package study.daydayup.wolf.business.trade.api.vo.buy;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.domain.VO;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.api.vo.buy
 *
 * @author Wingle
 * @since 2019/12/13 3:24 下午
 **/
@Data
@Builder
public class TradeGoods {
    private long sellId;
    private long goodsId;
    protected long categoryId;
    private String goodsName;
    private int goodsType;

    private long salePrice;

    private int currency;
    private int chargeUnit;

    private String goodsMainPic;
    private int goodsVersion;
    private String goodsCode;

    private long payPrice;
    private long postage;

    private int giftFlag;
    private int quantity;
    private long promotionId;
    private GoodsMemo memo;

    private TradeSku sku;
    private TradeLoan loan;
    private List<TradeInstallment> installmentList;
}
