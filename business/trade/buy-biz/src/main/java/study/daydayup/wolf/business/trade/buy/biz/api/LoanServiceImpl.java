package study.daydayup.wolf.business.trade.buy.biz.api;

import lombok.NonNull;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.domain.event.base.PaidEvent;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResponse;
import study.daydayup.wolf.business.trade.api.service.buy.LoanService;
import study.daydayup.wolf.business.trade.buy.biz.loan.entity.LoanContractEntity;
import study.daydayup.wolf.business.trade.buy.biz.loan.entity.LoanOrderEntity;
import study.daydayup.wolf.business.trade.buy.biz.loan.repository.LoanContractRepository;
import study.daydayup.wolf.business.trade.buy.biz.loan.repository.LoanOrderRepository;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.buy.biz.api
 *
 * @author Wingle
 * @since 2019/12/30 5:11 下午
 **/
@RpcService(protocol = "dubbo")
public class LoanServiceImpl implements LoanService {
    @Resource
    private LoanContractRepository loanContractRepository;
    @Resource
    private LoanOrderRepository loanOrderRepository;

    @Override
    public Result<Contract> find(@NonNull TradeId tradeId) {
        tradeId.valid();
        LoanContractEntity contractEntity = loanContractRepository.find(tradeId);
        if (contractEntity == null) {
            return Result.ok(null);
        }

        return Result.ok(contractEntity.getModel());
    }

    @Override
    public void approve(@Validated TradeId tradeId) {
        tradeId.valid();
        LoanContractEntity entity = loanContractRepository.find(tradeId);
        entity.approve();

        loanContractRepository.save(entity);
    }

    @Override
    public void refuse(TradeId tradeId) {
        tradeId.valid();
        LoanContractEntity entity = loanContractRepository.find(tradeId);
        entity.refuse();

        loanContractRepository.save(entity);
    }

    @Override
    public void startLoan(TradeId tradeId) {
        tradeId.valid();

        LoanContractEntity contract = loanContractRepository.find(tradeId);
        contract.startLoan();
        loanContractRepository.save(contract);

        LoanOrderEntity order = new LoanOrderEntity(contract.getModel());
        order.loan();
        loanOrderRepository.save(order);
    }

    @Override
    public void completeLoan(TradeId tradeId) {
        tradeId.valid();
        LoanContractEntity entity = loanContractRepository.find(tradeId);
        entity.completeLoan();

        loanContractRepository.save(entity);
    }

    @Override
    public void createLoanProxy() {
        //TODO
    }

    @Override
    public Result<PayResponse> repay(TradeId tradeId, Integer installmentNo) {
        tradeId.valid();
        if (installmentNo == null || installmentNo < 1) {
            throw new IllegalArgumentException("installmentNo can't be null");
        }

        LoanContractEntity contract = loanContractRepository.find(tradeId);
        LoanOrderEntity order = new LoanOrderEntity(contract.getModel());
        order.repay(installmentNo);
        loanOrderRepository.save(order);

        return null;
    }

    @Override
    public void due(TradeId tradeId, Integer installmentNo) {
        tradeId.valid();
        if (installmentNo == null || installmentNo < 1) {
            throw new IllegalArgumentException("installmentNo can't be null");
        }

        LoanContractEntity contract = loanContractRepository.find(tradeId);
        LoanOrderEntity order = new LoanOrderEntity(contract.getModel());
        order.repay(installmentNo);
        loanOrderRepository.save(order);
    }

    @Override
    public void overdue(TradeId tradeId, Integer installmentNo) {
        tradeId.valid();
        if (installmentNo == null || installmentNo < 1) {
            throw new IllegalArgumentException("installmentNo can't be null");
        }
    }

    @Override
    public void markAsLoss(TradeId tradeId, Integer installmentNo) {
        tradeId.valid();
        if (installmentNo == null || installmentNo < 1) {
            throw new IllegalArgumentException("installmentNo can't be null");
        }
    }

    @Override
    public void scanDueLoan() {

    }

    @Override
    public void scanOverdueLoan() {

    }

    @Override
    public void subscribePaidEvent(PaidEvent event) {

    }

    @Override
    public void subscribeLoanEvent() {

    }

    @Override
    public void subscribeDueEvent() {

    }

    @Override
    public void subscribeOverdueEvent() {

    }

    @Override
    public void subscribeLossEvent() {

    }
}
