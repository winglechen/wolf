package study.daydayup.wolf.business.trade.order.biz.domain.repository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.trade.api.dto.ContractOption;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.domain.exception.InvalidContractException;
import study.daydayup.wolf.business.trade.api.domain.state.TradeState;
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

    public void save(Contract key, Contract changes) {
        if (key == null || null == changes) {
            return;
        }
        updateContract(key, changes);

//        consignTermRepository.save(key.getConsignTerm(), changes.getConsignTerm());
        installmentTermRepository.save(key.getInstallmentTermList(), changes.getInstallmentTermList());
//        loanTermRepository.save(key.getLoanTerm(), changes.getLoanTerm());
//        objectsTermRepository.save(key.getObjectsTerm(), changes.getObjectsTerm());
//        paymentTermRepository.save(key.getPaymentTerm(), changes.getPaymentTerm());
//        postageTermRepository.save(key.getPostageTerm(), changes.getPostageTerm());
//        repaymentTermRepository.save(key.getRepaymentTerm(), changes.getRepaymentTerm());
//        taxTermRepository.save(key.getTaxTerm(), changes.getTaxTerm());
    }

    public Contract find(TradeId tradeId) {
        return find(tradeId, null);
    }

    public Contract find(TradeId tradeId, ContractOption option) {
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

    private int updateContract(@Validated Contract key, Contract changes) {
        ContractDO keyDO = modelToDO(key);
        ContractDO changesDO = modelToDO(changes);
        changesDO.setUpdatedAt(LocalDateTime.now());

        TradeState state = Tsm.getStateByEvent(key.getTradeType(), key.getState(), changes.getStateEvent());
        if (state != null) {
            changesDO.setState(state.getCode());

            if (key.getState() != null) {
                keyDO.setState(key.getState().getCode());
            }
        }

        return contractDAO.updateByKey(changesDO, keyDO);
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
