package study.daydayup.wolf.business.trade.buy.biz.api;

import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.event.base.PaidEvent;
import study.daydayup.wolf.business.trade.api.service.buy.LoanService;
import study.daydayup.wolf.business.trade.buy.biz.loan.entity.LoanEntity;
import study.daydayup.wolf.business.trade.buy.biz.loan.entity.OrderEntity;
import study.daydayup.wolf.business.trade.buy.biz.loan.repository.LoanRepository;
import study.daydayup.wolf.business.trade.buy.biz.loan.repository.OrderRepository;
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
    private LoanRepository loanRepository;
    @Resource
    private OrderRepository orderRepository;

    @Override
    public void approve(@Validated TradeId tradeId) {
        tradeId.valid();
        LoanEntity entity = loanRepository.find(tradeId);
        entity.approve();

        loanRepository.save(entity);
    }

    @Override
    public void refuse(TradeId tradeId) {
        tradeId.valid();
        LoanEntity entity = loanRepository.find(tradeId);
        entity.refuse();

        loanRepository.save(entity);
    }

    @Override
    public void startLoan(TradeId tradeId) {
        tradeId.valid();

        LoanEntity contract = loanRepository.find(tradeId);
        contract.startLoan();
        loanRepository.save(contract);

        OrderEntity order = new OrderEntity(contract.getModel());
        order.loan();
        orderRepository.save(order);
    }

    @Override
    public void completeLoan(TradeId tradeId) {
        tradeId.valid();
        LoanEntity entity = loanRepository.find(tradeId);
        entity.completeLoan();

        loanRepository.save(entity);
    }

    @Override
    public void createLoanProxy() {
        //TODO
    }

    @Override
    public void prepay(TradeId tradeId, Integer installmentNo) {
        tradeId.valid();
        if (installmentNo == null || installmentNo < 1) {
            throw new IllegalArgumentException("installmentNo can't be null");
        }

        LoanEntity contract = loanRepository.find(tradeId);
        OrderEntity order = new OrderEntity(contract.getModel());
        order.repay(installmentNo);
        orderRepository.save(order);
    }

    @Override
    public void due(TradeId tradeId, Integer installmentNo) {
        tradeId.valid();
        if (installmentNo == null || installmentNo < 1) {
            throw new IllegalArgumentException("installmentNo can't be null");
        }

        LoanEntity contract = loanRepository.find(tradeId);
        OrderEntity order = new OrderEntity(contract.getModel());
        order.repay(installmentNo);
        orderRepository.save(order);
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
