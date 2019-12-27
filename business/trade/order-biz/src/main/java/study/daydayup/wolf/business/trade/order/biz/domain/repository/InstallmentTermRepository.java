package study.daydayup.wolf.business.trade.order.biz.domain.repository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.exception.InvalidContractException;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.business.trade.api.vo.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.order.biz.dal.dao.InstallmentTermDAO;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.InstallmentTermDO;
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
        for (InstallmentTermDO installmentTermDO: installmentTermDOList) {
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


    }

    public List<InstallmentTerm> find(TradeId tradeId) {
        tradeId.valid();

        List<InstallmentTermDO> installmentTermDOList = installmentTermDAO.selectByTradeNo(
                tradeId.getTradeNo(), tradeId.getBuyerId(), tradeId.getSellerId()
        );

        return batchDOToModel(installmentTermDOList, true);
    }

    private InstallmentTerm DOToModel(InstallmentTermDO installmentTermDO) {
        if (installmentTermDO == null) {
            return null;
        }

        InstallmentTerm installmentTerm = new InstallmentTerm();

        BeanUtils.copyProperties(installmentTermDO, installmentTerm);
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
