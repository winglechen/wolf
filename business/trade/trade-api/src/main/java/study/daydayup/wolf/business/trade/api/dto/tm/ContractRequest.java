package study.daydayup.wolf.business.trade.api.dto.tm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.dto.order.ContractOption;
import study.daydayup.wolf.framework.layer.api.Request;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.trade.api.dto.tm
 *
 * @author Wingle
 * @since 2020/1/9 7:38 下午
 **/
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class ContractRequest extends TradeRequest implements Request {
    private ContractOption option;

    private Integer loanState;
    private Integer loanType;

    private String loanBefore;
    private String loanAfter;

    private Integer loanVersion;

    private Integer installmentNo;
    private Integer repayState;
    private Integer repayType;

    private String repayDueBefore;
    private String repayDueAfter;
    private String repayDue;
    private LocalDateTime repayDueAt;
    private LocalDateTime repayDueStart;
    private LocalDateTime repayDueEnd;

    private Integer repayVersion;
}
