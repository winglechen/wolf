package study.daydayup.wolf.business.uc.crm.biz.customer.info.api.india;

import lombok.NonNull;
import study.daydayup.wolf.business.uc.api.crm.customer.info.dto.india.IndianBankCard;
import study.daydayup.wolf.business.uc.api.crm.customer.info.service.india.IndianCustomerService;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao.AadhaarDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao.BankCardDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.AadhaarDO;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.BankCardDO;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.info.api.india
 *
 * @author Wingle
 * @since 2020/3/22 9:49 下午
 **/
@RpcService(protocol = "dubbo")
public class IndianCustomerServiceImpl implements IndianCustomerService {
    @Resource
    private AadhaarDAO aadhaarDAO;
    @Resource
    private BankCardDAO bankCardDAO;

    @Override
    public Result<IndianBankCard> findIndianBankCard(Long accountId, Long orgId) {
        IndianBankCard card = IndianBankCard.builder()
                .accountId(accountId)
                .orgId(orgId)
                .build();

        card = findAadhaarByCard(card);
        if (card == null) {
            return Result.fail(10000, "no such card");
        }

        card = findBankCardByCard(card);
        if (card == null) {
            return Result.fail(10000, "no such card");
        }

        return Result.ok(card);
    }

    private IndianBankCard findAadhaarByCard(@NonNull IndianBankCard card) {
        AadhaarDO aadhaarDO = aadhaarDAO.selectByAccountIdAndOrgId(card.getAccountId(), card.getOrgId());
        if (aadhaarDO == null
            || null == aadhaarDO.getAadhaarNo()
            || null == aadhaarDO.getName()) {
            return null;
        }

        card.setAadhaarNo(aadhaarDO.getAadhaarNo());
        card.setAadhaarName(aadhaarDO.getName());
        return card;
    }

    private IndianBankCard findBankCardByCard(@NonNull IndianBankCard card) {
        BankCardDO cardDO = bankCardDAO.selectByAccountIdAndOrgId(card.getAccountId(), card.getOrgId());
        if (cardDO == null
            || null == cardDO.getIfsc()
            || null == cardDO.getBankNo()) {
            return null;
        }

        card.setAccountIfsc(cardDO.getIfsc());
        card.setBankName(cardDO.getBankName());
        card.setAccountNumber(cardDO.getBankNo());
        return card;
    }
}
