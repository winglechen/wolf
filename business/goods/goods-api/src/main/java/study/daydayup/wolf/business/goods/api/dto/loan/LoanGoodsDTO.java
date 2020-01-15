package study.daydayup.wolf.business.goods.api.dto.loan;

import study.daydayup.wolf.business.goods.api.entity.goods.LoanGoods;
import study.daydayup.wolf.framework.layer.api.DTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.dto.request
 *
 * @author Wingle
 * @since 2020/1/15 10:10 上午
 **/
public class LoanGoodsDTO extends LoanGoods implements DTO {
    /**
     * 精度 0.00
     * convert to Long
     *    method 1:
     *          Decimal.of(price * 10000, 0).toLong()
     *    method 2:
     *          new RMB(price, RMBEnum.YUAN).toLi().toLong();
     */
    private BigDecimal price;

    private LoanDTO loan;
    private List<InstallmentDTO> installmentList;
}
