package study.daydayup.wolf.business.goods.api.entity.goods;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.business.goods.api.entity.Goods;
import study.daydayup.wolf.business.goods.api.vo.Installment;
import study.daydayup.wolf.business.goods.api.vo.Loan;

import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.entity.goods
 *
 * @author Wingle
 * @since 2019/12/11 5:17 下午
 **/
@Data
public class LoanGoods extends Goods {
    @Builder
    public LoanGoods(){}

    private Loan loan;
    private List<Installment> installmentList;
}
