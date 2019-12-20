package study.daydayup.wolf.business.goods.api.dto.request;

import lombok.Data;
import study.daydayup.wolf.business.goods.api.vo.Installment;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.dto.request
 *
 * @author Wingle
 * @since 2019/12/10 8:56 下午
 **/
@Data
@Deprecated
public class LoanCreateRequest extends GoodsCreateRequest {
    //loan add by user
    @Min(1)
    private int handlingFeeRate;
    @Min(1)
    private int period;
    @Min(1)
    private int interest;
    @Min(1)
    private int penalty;

    //loan defined in the config
    private int interestUnit;
    private int penaltyUnit;
    private int periodUnit;
    private int periodStrategy;
    private int repayStrategy;
    private int prepayStrategy;
    private int amountStrategy;
    private int feePayStrategy;

    private List<Installment> installments;









}
