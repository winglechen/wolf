package study.daydayup.wolf.business.goods.api.vo;

import lombok.Data;
import study.daydayup.wolf.business.goods.api.enums.InstallmentTypeEnum;
import study.daydayup.wolf.framework.layer.domain.Entity;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.goods.api.dto.request
 *
 * @author Wingle
 * @since 2019/12/10 9:15 下午
 **/
@Data
public class Installment implements Entity  {
    @Min(1)
    private Integer period;
    @DecimalMin("0.0001") @DecimalMax("100")
    private BigDecimal percentage;
    @DecimalMin("0.0001") @DecimalMax("100")
    private BigDecimal feePercentage;

    /**
     * @see study.daydayup.wolf.business.goods.api.enums.InstallmentTypeEnum
     */
    private Integer type = InstallmentTypeEnum.DEFAULT.getCode();
}
