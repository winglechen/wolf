package study.daydayup.wolf.business.goods.api.vo;

import lombok.Data;
import study.daydayup.wolf.framework.layer.domain.Entity;

import javax.validation.constraints.Min;

/**
 * study.daydayup.wolf.business.goods.api.dto.request
 *
 * @author Wingle
 * @since 2019/12/10 9:15 下午
 **/
@Data
public class Installment extends Entity {
    @Min(1)
    private int duration;
    @Min(1)
    private int percentage;
    @Min(1)
    private int feePercentage;
}
