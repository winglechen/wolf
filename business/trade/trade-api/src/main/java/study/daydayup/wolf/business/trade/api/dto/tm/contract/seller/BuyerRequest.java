package study.daydayup.wolf.business.trade.api.dto.tm.contract.seller;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.SellerRequest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.trade.api.dto.tm.contract.seller
 *
 * @author Wingle
 * @since 2020/1/14 12:18 下午
 **/
@Data
public class BuyerRequest extends SellerRequest {
    @NotNull @Min(1)
    private Long buyerId;

    private Integer state;
    private Integer tradeType;

    private LocalDateTime createdBefore;
    private LocalDateTime createdAfter;
}
