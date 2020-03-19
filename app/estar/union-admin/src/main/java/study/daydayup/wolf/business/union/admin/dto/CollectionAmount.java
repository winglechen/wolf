package study.daydayup.wolf.business.union.admin.dto;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.union.admin.dto
 *
 * @author Wingle
 * @since 2020/3/12 12:46 上午
 **/
@Data
public class CollectionAmount implements Request {
    @NotNull @DecimalMin("0.01")
    private BigDecimal amount;
}
