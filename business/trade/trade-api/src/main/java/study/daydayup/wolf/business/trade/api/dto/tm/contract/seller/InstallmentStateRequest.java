package study.daydayup.wolf.business.trade.api.dto.tm.contract.seller;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.SellerRequest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * study.daydayup.wolf.business.trade.api.dto.tm.contract
 *
 * @author Wingle
 * @since 2020/1/14 10:51 上午
 **/
@Data
public class InstallmentStateRequest extends SellerRequest {
    private Long buyerId;
    private Integer state;
    protected Integer installmentType;
    @NotNull
    private LocalDate dueAt;
}
