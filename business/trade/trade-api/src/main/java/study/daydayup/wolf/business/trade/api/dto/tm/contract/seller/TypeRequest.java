package study.daydayup.wolf.business.trade.api.dto.tm.contract.seller;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.SellerRequest;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.trade.api.dto.tm.contract
 *
 * @author Wingle
 * @since 2020/1/14 10:52 上午
 **/
@Data
public class TypeRequest extends SellerRequest {
    @NotNull @Min(1)
    private Integer tradeType;

    private Integer state;
    private LocalDateTime createdBefore;
    private LocalDateTime createdAfter;
}
