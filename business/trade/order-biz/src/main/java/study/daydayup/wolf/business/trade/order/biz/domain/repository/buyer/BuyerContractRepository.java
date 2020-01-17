package study.daydayup.wolf.business.trade.order.biz.domain.repository.buyer;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.TradeOwner;
import study.daydayup.wolf.business.trade.order.biz.dal.dao.ContractDAO;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.ContractDO;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.ContractQueryRepository;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository.buyer
 *
 * @author Wingle
 * @since 2020/1/17 8:46 下午
 **/
@Component
public class BuyerContractRepository extends ContractQueryRepository {
    @Resource
    private ContractDAO contractDAO;

    public Page<Contract> findAll(@NonNull Long buyerId,  PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        List<ContractDO> contractDOList = contractDAO.selectByBuyerId(buyerId);
        if (contractDOList == null || contractDOList.isEmpty()) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }

        TradeOwner owner = new TradeOwner();
        owner.setBuyerId(buyerId);

        List<Contract> contractList = findTermsByContractList(contractDOList, owner);
        return Page.of(contractDOList).to(contractList);
    }

    public Contract findLatest(@NonNull Long buyerId) {
        ContractDO contractDO = contractDAO.selectLatestByBuyer(buyerId);
        if (contractDO == null) {
            return null;
        }

        TradeId tradeId = new TradeId();
        tradeId.setTradeNo(contractDO.getTradeNo());
        tradeId.setBuyerId(contractDO.getBuyerId());

        return findTermsByContract(contractDO, tradeId);
    }
}
