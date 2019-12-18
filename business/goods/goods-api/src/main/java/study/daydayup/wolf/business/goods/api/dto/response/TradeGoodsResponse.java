package study.daydayup.wolf.business.goods.api.dto.response;

import lombok.Data;
import study.daydayup.wolf.business.goods.api.entity.Sku;
import study.daydayup.wolf.business.goods.api.entity.goods.BaseGoods;
import study.daydayup.wolf.business.goods.api.vo.Installment;
import study.daydayup.wolf.business.goods.api.vo.Loan;
import study.daydayup.wolf.framework.layer.api.Response;
import study.daydayup.wolf.model.annotation.column.ForeignKey;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.dto.response
 *
 * @author Wingle
 * @since 2019/12/12 10:09 上午
 **/
@Data
public class TradeGoodsResponse extends BaseGoods {
    protected Sku sku;
    protected Loan loan;
    protected List<Installment> installmentList;

    private long postage = 0;

}
