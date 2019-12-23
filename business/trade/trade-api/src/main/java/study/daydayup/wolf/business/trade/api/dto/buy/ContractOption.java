package study.daydayup.wolf.business.trade.api.dto.buy;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy
 *
 * @author Wingle
 * @since 2019/12/16 5:33 下午
 **/
@Data
public class ContractOption implements Request {
    private boolean withConsignTerm;
    private boolean withInstallmentTerm;
    private boolean withLoanTerm;
    private boolean withObjectsTerm;
    private boolean withPaymentTerm;
    private boolean withPostageTerm;
    private boolean withTaxTerm;

    private boolean withStateLog;
}
