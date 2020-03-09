package study.daydayup.wolf.business.trade.order.biz.domain.repository;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.*;
import study.daydayup.wolf.business.trade.api.dto.TradeOwner;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.TradeIds;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.ContractDO;
import study.daydayup.wolf.business.trade.order.biz.domain.entity.ContractEntity;
import study.daydayup.wolf.business.trade.order.biz.domain.factory.ContractFactory;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.collection.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository.core
 *
 * @author Wingle
 * @since 2020/1/17 8:56 下午
 **/
@Component
public class ContractQueryRepository extends ContractRepository {
    protected List<Contract> findTermsByContractList(List<ContractDO> contractDOList, @NonNull TradeOwner owner) {
        if (contractDOList.isEmpty()) {
            return ListUtil.empty();
        }

        List<Contract> contractList = converter.toModel(contractDOList);
        TradeIds tradeIds = initTradeIds(contractDOList, owner);
        if (tradeIds == null) {
            return contractList;
        }

        findLoanTermsByContractList(contractList, tradeIds);
        findInstallmentTermsByContractList(contractList, tradeIds);
        findConsignTermsByContractList(contractList, tradeIds);
        findObjectsTermsByContractList(contractList, tradeIds);
        findPaymentTermsByContractList(contractList, tradeIds);
        findRepaymentTermsByContractList(contractList, tradeIds);
        findPostageTermsByContractList(contractList, tradeIds);
        findTaxTermsByContractList(contractList, tradeIds);

        return formatContractList(contractList);
    }

    private List<Contract> formatContractList(List<Contract> contractList) {
        List<ContractEntity> entityList = ContractFactory.create(contractList);

        List<Contract> result = new ArrayList<>(entityList.size());
        for (ContractEntity entity : entityList) {
            result.add(entity.getModel());
        }

        return result;
    }

    private TradeIds initTradeIds(@NonNull List<ContractDO> contractDOList,  @NonNull TradeOwner owner) {
        List<String> tradeNos = CollectionUtil.keys(contractDOList, ContractDO::getTradeNo);
        if (!ListUtil.notEmpty(tradeNos)) {
            return null;
        }

        TradeIds tradeIds = new TradeIds();
        tradeIds.setBuyerId(owner.getBuyerId());
        tradeIds.setSellerId(owner.getSellerId());
        tradeIds.addAll(tradeNos);

        return tradeIds;
    }

    private void findLoanTermsByContractList(List<Contract> contractList, TradeIds tradeIds) {
        List<LoanTerm> termList = loanTermRepository.find(tradeIds);
        if (!ListUtil.notEmpty(termList)) {
            return;
        }

        CollectionUtil.join(contractList, Contract::getTradeNo, Contract::setLoanTerm, termList, LoanTerm::getTradeNo);
    }

    private void findInstallmentTermsByContractList(List<Contract> contractList, TradeIds tradeIds) {
        List<InstallmentTerm> termList = installmentTermRepository.find(tradeIds);
        if (!ListUtil.notEmpty(termList)) {
            return;
        }
        Map<String, List<InstallmentTerm>> termListMap = CollectionUtil.group(termList, InstallmentTerm::getTradeNo);
        CollectionUtil.join(contractList, Contract::getTradeNo, Contract::setInstallmentTermList, termListMap);
    }

    private void findConsignTermsByContractList(List<Contract> contractList, TradeIds tradeIds) {
        List<ConsignTerm> termList = consignTermRepository.find(tradeIds);
        if (!ListUtil.notEmpty(termList)) {
            return;
        }
    }

    private void findObjectsTermsByContractList(List<Contract> contractList, TradeIds tradeIds) {
        List<ObjectsTerm> termList = objectsTermRepository.find(tradeIds);
        if (!ListUtil.notEmpty(termList)) {
            return;
        }
    }

    private void findPaymentTermsByContractList(List<Contract> contractList, TradeIds tradeIds) {
        List<PaymentTerm> termList = paymentTermRepository.find(tradeIds);
        if (!ListUtil.notEmpty(termList)) {
            return;
        }
    }

    private void findRepaymentTermsByContractList(List<Contract> contractList, TradeIds tradeIds) {
        List<RepaymentTerm> termList = repaymentTermRepository.find(tradeIds);
        if (!ListUtil.notEmpty(termList)) {
            return;
        }
    }

    private void findPostageTermsByContractList(List<Contract> contractList, TradeIds tradeIds) {
        List<PostageTerm> termList = postageTermRepository.find(tradeIds);
        if (!ListUtil.notEmpty(termList)) {
            return;
        }
    }

    private void findTaxTermsByContractList(List<Contract> contractList, TradeIds tradeIds) {
        List<TaxTerm> termList = taxTermRepository.find(tradeIds);
        if (!ListUtil.notEmpty(termList)) {
            return;
        }
    }

}
