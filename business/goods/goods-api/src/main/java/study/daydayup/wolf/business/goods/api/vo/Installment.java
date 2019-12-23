package study.daydayup.wolf.business.goods.api.vo;

import lombok.Data;
import study.daydayup.wolf.business.goods.api.enums.InstallmentTypeEnum;
import study.daydayup.wolf.framework.layer.domain.Entity;

import javax.validation.constraints.Min;

/**
 * study.daydayup.wolf.business.goods.api.dto.request
 *
 * @author Wingle
 * @since 2019/12/10 9:15 下午
 **/
@Data
public class Installment implements Entity  {
    @Min(1)
    private int period;
    @Min(1)
    private int percentage;
    @Min(1)
    private int feePercentage;

    /**
     * @see study.daydayup.wolf.business.goods.api.enums.InstallmentTypeEnum
     */
    private int type = InstallmentTypeEnum.DEFAULT.getCode();
}
