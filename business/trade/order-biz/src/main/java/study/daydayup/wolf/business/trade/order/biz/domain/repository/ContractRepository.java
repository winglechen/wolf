package study.daydayup.wolf.business.trade.order.biz.domain.repository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.entity.Contract;
import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.exception.InvalidContractException;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.business.trade.order.biz.dal.dao.ContractDAO;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.ContractDO;
import study.daydayup.wolf.business.trade.order.biz.tsm.Tsm;
import study.daydayup.wolf.common.sm.StateMachine;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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
        insertContract(contract);

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
        if (locker == null || null == changes) {
            return;
        }
        updateContract(locker, changes);

//        consignTermRepository.save(locker.getConsignTerm(), changes.getConsignTerm());
        installmentTermRepository.save(locker.getInstallmentTermList(), changes.getInstallmentTermList());
//        loanTermRepository.save(locker.getLoanTerm(), changes.getLoanTerm());
//        objectsTermRepository.save(locker.getObjectsTerm(), changes.getObjectsTerm());
//        paymentTermRepository.save(locker.getPaymentTerm(), changes.getPaymentTerm());
//        postageTermRepository.save(locker.getPostageTerm(), changes.getPostageTerm());
//        repaymentTermRepository.save(locker.getRepaymentTerm(), changes.getRepaymentTerm());
//        taxTermRepository.save(locker.getTaxTerm(), changes.getTaxTerm());
    }

    public Contract find(TradeId tradeId) {
        ContractDO contractDO = findContract(tradeId);
        if (contractDO == null) {
            return null;
        }

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
        BeanUtils.copyProperties(contractDO, contract);

        return contract;
    }

    private ContractDO findContract(TradeId tradeId) {
        tradeId.valid();

        return contractDAO.selectByTradeNo(tradeId.getTradeNo(), tradeId.getBuyerId(), tradeId.getSellerId());
    }

    private void insertContract(@Validated Contract contract) {
        ContractDO contractDO = new ContractDO();
        BeanUtils.copyProperties(contract, contractDO);

        StateMachine<TradeState, TradeEvent> stateMachine = Tsm.create(contract.getTradeType());
        TradeState initState = stateMachine.getInitState();
        contractDO.setState(initState.getCode());

        contractDAO.insertSelective(contractDO);
    }

    private int updateContract(@Validated Contract locker, Contract changes) {
        ContractDO lockerDO = modelToDO(locker);
        ContractDO changesDO = modelToDO(changes);
        changesDO.setUpdatedAt(LocalDateTime.now());

        TradeState state = getTradeState(locker.getTradeType(), changes.getStateEvent(), locker.getState());
        if (state != null) {
            changesDO.setState(state.getCode());

            if (locker.getState() != null) {
                lockerDO.setState(locker.getState().getCode());
            }
        }

        return contractDAO.updateByTradeNo(changesDO, lockerDO);
    }

    private TradeState getTradeState(Integer tradeType, TradeEvent event, TradeState state) {
        if (null == tradeType || event == null) {
            return null;
        }
        StateMachine<TradeState, TradeEvent> stateMachine = Tsm.create(tradeType);
        if (state == null) {
            return stateMachine.getInitState();
        }

        return stateMachine.fire(state, event);
    }

    private ContractDO modelToDO(Contract contract) {
        if (contract == null) {
            throw new InvalidContractException("contract is null");
        }

        ContractDO contractDO = new ContractDO();
        BeanUtils.copyProperties(contract, contractDO);

        return contractDO;
    }


}
