package study.daydayup.wolf.business.trade.api.domain.vo.buy;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.framework.layer.domain.VO;

import java.math.BigDecimal;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.api.domain.vo.buy
 *
 * @author Wingle
 * @since 2019/12/13 3:24 下午
 **/
@Data
@Builder
public class Goods implements VO {
    private Long  sellId;
    private Long  goodsId;
    protected Long  categoryId;
    private String goodsName;
    private Integer goodsType;

    private BigDecimal salePrice;

    private Integer currency;
    private Integer chargeUnit;

    private String goodsMainPic;
    private Integer goodsVersion;
    private String goodsCode;

    private BigDecimal  payPrice;
    private BigDecimal  postage;

    private Integer giftFlag;
    private Integer quantity;
    private Long  promotionId;
    private GoodsMemo memo;

    private Sku sku;
    private Loan loan;
    private List<Installment> installmentList;
}
