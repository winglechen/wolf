package study.daydayup.wolf.business.trade.api.dto.tm.trade.seller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.SellerRequest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.trade.api.dto.tm.contract
 *
 * @author Wingle
 * @since 2020/1/14 10:51 上午
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class StateRequest extends SellerRequest {
    @NotNull @Min(1)
    private Integer state;

    private Integer tradeType;
    private LocalDateTime createdBefore;
    private LocalDateTime createdAfter;
}
