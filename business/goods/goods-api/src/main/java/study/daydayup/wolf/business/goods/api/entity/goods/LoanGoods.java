package study.daydayup.wolf.business.goods.api.entity.goods;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.goods.api.entity.Goods;
import study.daydayup.wolf.business.goods.api.vo.Installment;
import study.daydayup.wolf.business.goods.api.vo.Loan;

import java.io.Serializable;
import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.entity.goods
 *
 * @author Wingle
 * @since 2019/12/11 5:17 下午
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder(toBuilder = true)
public class LoanGoods extends Goods implements Serializable {
    public LoanGoods(){}

    private Loan loan;
    private List<Installment> installmentList;
}
