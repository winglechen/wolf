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
    private Integer handlingFeeRate;
    @Min(1)
    private Integer period;
    @Min(1)
    private Integer interest;
    @Min(1)
    private Integer penalty;

    //loan defined in the config
    private Integer interestUnit;
    private Integer penaltyUnit;
    private Integer periodUnit;
    private Integer periodStrategy;
    private Integer repayStrategy;
    private Integer prepayStrategy;
    private Integer amountStrategy;
    private Integer feePayStrategy;

    private List<Installment> installments;









}
