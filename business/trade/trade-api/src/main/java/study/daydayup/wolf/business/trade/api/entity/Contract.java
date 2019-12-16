package study.daydayup.wolf.business.trade.api.entity;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.entity.contract.BaseContract;
import study.daydayup.wolf.business.trade.api.vo.contract.*;

/**
 * study.daydayup.wolf.business.trade.api.entity
 *
 * @author Wingle
 * @since 2019/10/4 12:04 AM
 **/
@Data
public class Contract extends BaseContract {
    private ObjectsTerm objectsTerm;
    private PaymentTerm paymentTerm;

    private ConsignTerm consignTerm;
    private PostageTerm postageTerm;

    private LoanTerm loanTerm;
    private RepaymentTerm repaymentTerm;
    private InstallmentTerm installmentTerm;
    private TaxTerm taxTerm;
}
