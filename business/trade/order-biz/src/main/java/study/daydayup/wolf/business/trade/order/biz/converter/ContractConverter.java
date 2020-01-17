package study.daydayup.wolf.business.trade.order.biz.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.domain.state.TradeState;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.ContractDO;
import study.daydayup.wolf.business.trade.order.biz.tsm.Tsm;
import study.daydayup.wolf.common.sm.StateMachine;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.ArrayList;
import java.util.List;


/**
 * study.daydayup.wolf.business.trade.order.biz.converter
 *
 * @author Wingle
 * @since 2020/1/14 2:55 下午
 **/
@Component
public class ContractConverter implements Converter {

    public ContractDO toDo(Contract contract) {
        if (contract == null) {
            return null;
        }

        ContractDO contractDO = new ContractDO();
        BeanUtils.copyProperties(contract, contractDO);

        if (null != contract.getState()) {
            contractDO.setState(contract.getState().getCode());
        }

        return contractDO;
    }

    public Contract toModel(ContractDO contractDO) {
        if (contractDO == null) {
            return null;
        }

        Contract contract = new Contract();
        BeanUtils.copyProperties(contractDO, contract);

        StateMachine<TradeState, TradeEvent> stateMachine = Tsm.create(contractDO.getTradeType());
        contract.setState(stateMachine.getStateByCode(contractDO.getState()));

        return contract;
    }

    public List<Contract> toModel(List<ContractDO> contractDOList) {
        List<Contract> contracts = new ArrayList<>();
        if (contractDOList == null || contractDOList.isEmpty()) {
            return contracts;
        }

        for (ContractDO contractDO: contractDOList) {
            Contract contract = toModel(contractDO);
            if (contract == null) {
                continue;
            }
            contracts.add(contract);
        }

        return contracts;
    }
}
