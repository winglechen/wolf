package study.daydayup.wolf.business.trade.order.biz.domain.repository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.exception.InvalidContractException;
import study.daydayup.wolf.business.trade.api.exception.order.TradeStateNotFoundException;
import study.daydayup.wolf.business.trade.api.exception.order.UnsupportedStateChangeException;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.business.trade.api.entity.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.order.biz.dal.dao.InstallmentTermDAO;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.InstallmentTermDO;
import study.daydayup.wolf.business.trade.order.biz.tsm.Tsm;
import study.daydayup.wolf.common.sm.StateMachine;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository
 *
 * @author Wingle
 * @since 2019/12/26 9:06 下午
 **/
@Component
public class InstallmentTermRepository extends AbstractRepository implements Repository {
    @Resource
    private InstallmentTermDAO installmentTermDAO;

    /**
     * TODO: validation
     * @param installmentTerms installments
     */
    public void add(List<InstallmentTerm> installmentTerms) {
        if (installmentTerms == null || installmentTerms.isEmpty()) {
            return;
        }

        List<InstallmentTermDO>  installmentTermDOList = batchModelToDO(installmentTerms, true);
        StateMachine<TradeState, TradeEvent> stateMachine = Tsm.createForInstallment();
        Integer state = stateMachine.getInitState().getCode();

        for (InstallmentTermDO installmentTermDO: installmentTermDOList) {
            installmentTermDO.setState(state);
            installmentTermDAO.insertSelective(installmentTermDO);
        }
    }

    public void save(List<InstallmentTerm> lockers, List<InstallmentTerm> changes) {
        if (lockers == null || lockers.isEmpty()
                || null == changes || changes.isEmpty()
                || lockers.size() != changes.size()) {
            return;
        }

        List<InstallmentTermDO> lockerDOs = batchModelToDO(lockers, true);
        List<InstallmentTermDO> changeDOs = batchModelToDO(changes, true);
        if (lockers.size() != lockerDOs.size() || changes.size() != changeDOs.size()) {
            throw new InvalidContractException("Invalid installment: locker and change's size are not match");
        }

        TradeState state = getChangedState(lockers.get(0), changes.get(0));
        for (int i = 0, size=lockerDOs.size(); i < size; i++) {
            InstallmentTermDO lockerDO = lockerDOs.get(i);
            InstallmentTermDO changeDO = changeDOs.get(i);
            if (state != null) {
                changeDO.setState(state.getCode());
            }

            installmentTermDAO.updateByTradeNo(changeDO, lockerDO);
        }
    }

    public List<InstallmentTerm> find(TradeId tradeId) {
        tradeId.valid();

        List<InstallmentTermDO> installmentTermDOList = installmentTermDAO.selectByTradeNo(
                tradeId.getTradeNo(), tradeId.getBuyerId(), tradeId.getSellerId()
        );

        return batchDOToModel(installmentTermDOList, true);
    }

    private TradeState getChangedState(InstallmentTerm locker, InstallmentTerm change) {
        if (null == locker.getState() || null == change.getStateEvent()) {
            return null;
        }

        StateMachine<TradeState, TradeEvent> stateMachine = Tsm.create(TradeTypeEnum.INSTALLMENT_TERM);

        TradeState state = stateMachine.fire(locker.getState(), change.getStateEvent());
        if (state == null) {
            throw new UnsupportedStateChangeException(locker.getState(), change.getStateEvent());
        }

        return state;
    }

    private TradeState getByCode(Integer code) {
        if (code == null || code < 1) {
            return null;
        }

        StateMachine<TradeState, TradeEvent> stateMachine = Tsm.create(TradeTypeEnum.INSTALLMENT_TERM);
        TradeState state = stateMachine.getStateByCode(code);
        if (state == null) {
            throw new TradeStateNotFoundException(code);
        }

        return state;
    }

    private InstallmentTerm DOToModel(InstallmentTermDO installmentTermDO) {
        if (installmentTermDO == null) {
            return null;
        }

        InstallmentTerm installmentTerm = new InstallmentTerm();
        BeanUtils.copyProperties(installmentTermDO, installmentTerm);

        installmentTerm.setState(getByCode(installmentTermDO.getState()));

        return installmentTerm;
    }

    private InstallmentTermDO modelToDO(InstallmentTerm installmentTerm) {
        if (installmentTerm == null) {
            return null;
        }

        InstallmentTermDO installmentTermDO = new InstallmentTermDO();
        BeanUtils.copyProperties(installmentTerm, installmentTermDO);

        TradeState state =installmentTerm.getState();
        if (state != null) {
            installmentTermDO.setState(state.getCode());
        }

        return installmentTermDO;
    }

    private List<InstallmentTerm> batchDOToModel(List<InstallmentTermDO> installmentTermDOList, boolean filterNull) {
        if (installmentTermDOList == null || installmentTermDOList.isEmpty()) {
            return null;
        }

        List<InstallmentTerm> installmentTermList = new ArrayList<>();
        for (InstallmentTermDO installmentTermDO: installmentTermDOList) {
            InstallmentTerm installmentTerm = DOToModel(installmentTermDO);

            if (filterNull && null == installmentTerm) {
                continue;
            }
            installmentTermList.add(installmentTerm);
        }

        return installmentTermList;
    }

    private List<InstallmentTermDO> batchModelToDO(List<InstallmentTerm> installmentTermList, boolean filterNull) {
        if (installmentTermList == null || installmentTermList.isEmpty()) {
            return null;
        }

        List<InstallmentTermDO> installmentTermDOList = new ArrayList<>();
        for (InstallmentTerm installmentTerm : installmentTermList) {
            InstallmentTermDO installmentTermDO = modelToDO(installmentTerm);
            if (filterNull && null == installmentTermDO) {
                continue;
            }

            installmentTermDOList.add(installmentTermDO);
        }
        return installmentTermDOList;
    }

}
