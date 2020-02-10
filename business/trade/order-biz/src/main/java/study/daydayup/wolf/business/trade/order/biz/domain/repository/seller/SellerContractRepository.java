package study.daydayup.wolf.business.trade.order.biz.domain.repository.seller;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.dto.TradeOwner;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.BuyerRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.StateRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.TypeRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.TradeIds;
import study.daydayup.wolf.business.trade.order.biz.dal.dao.ContractDAO;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.ContractDO;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.ContractQueryRepository;
import study.daydayup.wolf.common.util.collection.ListUtil;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository.seller
 *
 * @author Wingle
 * @since 2020/1/18 10:35 下午
 **/
@Component
public class SellerContractRepository extends ContractQueryRepository {
    @Resource
    private ContractDAO contractDAO;

    public List<Contract> findByTradeNos(TradeIds tradeIds) {
        tradeIds.valid();
        List<ContractDO> contractDOList = contractDAO.selectByTradeNoIn(tradeIds.getTradeNoSet(), tradeIds.getBuyerId(), tradeIds.getSellerId());
        if (contractDOList == null || contractDOList.isEmpty()) {
            return ListUtil.empty();
        }

        TradeOwner owner = new TradeOwner();
        owner.setSellerId(tradeIds.getSellerId());
        owner.setBuyerId(tradeIds.getBuyerId());

        return findTermsByContractList(contractDOList, owner);
    }

    public Page<Contract> findAll(@NonNull Long sellerId, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<ContractDO> contractDOList = contractDAO.selectBySellerId(sellerId);
        if (contractDOList == null || contractDOList.isEmpty()) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }
        TradeOwner owner = new TradeOwner();
        owner.setSellerId(sellerId);

        List<Contract> contractList = findTermsByContractList(contractDOList, owner);
        return Page.of(contractDOList).to(contractList);
    }

    public Page<Contract> findByTradeType(TypeRequest request, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<ContractDO> contractDOList = contractDAO.sellerByTradeType(request);
        if (contractDOList == null || contractDOList.isEmpty()) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }
        TradeOwner owner = new TradeOwner();
        owner.setSellerId(request.getSellerId());

        List<Contract> contractList = findTermsByContractList(contractDOList, owner);
        return Page.of(contractDOList).to(contractList);
    }

    public Page<Contract> findByTradeState(StateRequest request, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<ContractDO> contractDOList = contractDAO.sellerByState(request);
        if (contractDOList == null || contractDOList.isEmpty()) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }
        TradeOwner owner = new TradeOwner();
        owner.setSellerId(request.getSellerId());

        List<Contract> contractList = findTermsByContractList(contractDOList, owner);
        return Page.of(contractDOList).to(contractList);
    }

    public Page<Contract> findByBuyerId(BuyerRequest request, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<ContractDO> contractDOList = contractDAO.sellerByBuyerId(request);
        if (contractDOList == null || contractDOList.isEmpty()) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }
        TradeOwner owner = new TradeOwner();
        owner.setSellerId(request.getSellerId());

        List<Contract> contractList = findTermsByContractList(contractDOList, owner);
        return Page.of(contractDOList).to(contractList);
    }

}
