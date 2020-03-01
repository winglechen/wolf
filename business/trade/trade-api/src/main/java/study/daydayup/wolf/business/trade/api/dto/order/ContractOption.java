package study.daydayup.wolf.business.trade.api.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy
 *
 * @author Wingle
 * @since 2019/12/16 5:33 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractOption implements Request {
    private boolean withConsignTerm         = false;
    private boolean withInstallmentTerm     = false;
    private boolean withLoanTerm            = false;
    private boolean withRepaymentTerm       = false;
    private boolean calculateRepayment      = false;
    private boolean withObjectsTerm         = false;
    private boolean withPaymentTerm         = false;
    private boolean withPostageTerm         = false;
    private boolean withTaxTerm             = false;

    private boolean withStateLog            = false;
}
