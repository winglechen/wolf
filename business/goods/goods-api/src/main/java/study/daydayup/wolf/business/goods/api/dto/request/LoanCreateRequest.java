package study.daydayup.wolf.business.goods.api.dto.request;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.dto.request
 *
 * @author Wingle
 * @since 2019/12/10 8:56 下午
 **/
@Data
public class LoanCreateRequest extends Request {
    @Min(1)
    private long orgId;
    @NotBlank
    private String name;
    @Min(1)
    private long price;

    //goods defined in the config
    private int currency;
    private int chargeUnit;

    //loan add by user
    @Min(1)
    private int handlingFeeRate;
    @Min(1)
    private int duration;
    @Min(1)
    private int interest;
    @Min(1)
    private int belatedPayment;


    //loan defined in the config
    private int interestUnit;
    private int belatedPaymentUnit;
    private int durationUnit;
    private int durationStrategy;
    private int repayStrategy;
    private int prepayStrategy;
    private int amountStrategy;
    private int feePayStrategy;

    private List<InstallmentRequest> installments;









}
