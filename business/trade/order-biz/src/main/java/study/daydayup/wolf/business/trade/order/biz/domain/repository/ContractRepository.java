package study.daydayup.wolf.business.trade.order.biz.domain.repository;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.entity.Contract;
import study.daydayup.wolf.business.trade.order.biz.dal.dao.ContractDAO;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository
 *
 * @author Wingle
 * @since 2019/12/26 9:06 下午
 **/
@Component
public class ContractRepository extends AbstractRepository implements Repository {
    @Resource
    private ConsignTermRepository consignTermRepository;
    @Resource
    private InstallmentTermRepository installmentTermRepository;
    @Resource
    private LoanTermRepository loanTermRepository;
    @Resource
    private ObjectsTermRepository objectsTermRepository;
    @Resource
    private PaymentTermRepository paymentTermRepository;
    @Resource
    private PostageTermRepository postageTermRepository;
    @Resource
    private RepaymentTermRepository repaymentTermRepository;
    @Resource
    private TaxTermRepository taxTermRepository;
    @Resource
    private ContractDAO contractDAO;

    public void add(Contract contract) {
        if (contract == null) {
            return;
        }

        //insert contract

        consignTermRepository.add(contract.getConsignTerm());
        installmentTermRepository.add(contract.getInstallmentTermList());
        loanTermRepository.add(contract.getLoanTerm());
        objectsTermRepository.add(contract.getObjectsTerm());
        paymentTermRepository.add(contract.getPaymentTerm());
        postageTermRepository.add(contract.getPostageTerm());
        repaymentTermRepository.add(contract.getRepaymentTerm());
        taxTermRepository.add(contract.getTaxTerm());
    }

    public void save(Contract locker, Contract changes) {

        consignTermRepository.save(locker.getConsignTerm(), changes.getConsignTerm());
        installmentTermRepository.save(locker.getInstallmentTermList(), changes.getInstallmentTermList());
        loanTermRepository.save(locker.getLoanTerm(), changes.getLoanTerm());
        objectsTermRepository.save(locker.getObjectsTerm(), changes.getObjectsTerm());
        paymentTermRepository.save(locker.getPaymentTerm(), changes.getPaymentTerm());
        postageTermRepository.save(locker.getPostageTerm(), changes.getPostageTerm());
        repaymentTermRepository.save(locker.getRepaymentTerm(), changes.getRepaymentTerm());
        taxTermRepository.save(locker.getTaxTerm(), changes.getTaxTerm());
    }

    public Contract find(TradeId tradeId) {
        Contract.ContractBuilder builder = Contract.builder()
                .consignTerm(consignTermRepository.find(tradeId))
                .installmentTermList(installmentTermRepository.find(tradeId))
                .loanTerm(loanTermRepository.find(tradeId))
                .objectsTerm(objectsTermRepository.find(tradeId))
                .paymentTerm(paymentTermRepository.find(tradeId))
                .postageTerm(postageTermRepository.find(tradeId))
                .repaymentTerm(repaymentTermRepository.find(tradeId))
                .taxTerm(taxTermRepository.find(tradeId))
                ;

        Contract contract = builder.build();

        return contract;
    }

}
