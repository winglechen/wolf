package study.daydayup.wolf.business.uc.crm.biz.customer.info.api.india;

import lombok.NonNull;
import study.daydayup.wolf.business.uc.api.crm.customer.info.dto.india.IndianBankInfo;
import study.daydayup.wolf.business.uc.api.crm.customer.info.service.india.IndianCustomerService;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao.AadhaarDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao.BankCardDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao.BasicInfoDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.AadhaarDO;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.BankCardDO;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.BasicInfoDO;
import study.daydayup.wolf.common.util.lang.StringUtil;
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
    @Resource
    private BasicInfoDAO basicInfoDAO;

    @Override
    public Result<IndianBankInfo> findIndianBankCard(Long accountId, Long orgId) {
        IndianBankInfo card = IndianBankInfo.builder()
                .accountId(accountId)
                .orgId(orgId)
                .build();

        card = findBasicInfoByCard(card);
        if (card == null) {
            return Result.fail(10000, "no such card");
        }

        card = findBankCardByCard(card);
        if (card == null) {
            return Result.fail(10000, "no such card");
        }

        return Result.ok(card);
    }

    @Override
    public Result<IndianBankInfo> findIndianAadhaar(Long accountId, Long orgId) {
        IndianBankInfo card = IndianBankInfo.builder()
                .accountId(accountId)
                .orgId(orgId)
                .build();

        card = findBasicInfoByCard(card);
        if (card == null) {
            return Result.fail(10000, "no such card");
        }
        return Result.ok(card);
    }

    private IndianBankInfo findBasicInfoByCard(@NonNull IndianBankInfo card) {
        BasicInfoDO basicInfoDO = basicInfoDAO.selectByAccountIdAndOrgId(card.getAccountId(), card.getOrgId());
        if (basicInfoDO == null
                || null == basicInfoDO.getAadhaarNo()
                || null == basicInfoDO.getFirstName()
                || null == basicInfoDO.getLastName()) {
            return null;
        }

        String name = StringUtil.joinSkipBlank(basicInfoDO.getFirstName(), basicInfoDO.getMiddleName(), basicInfoDO.getLastName());
        card.setAadhaarNo(basicInfoDO.getAadhaarNo());
        card.setAadhaarName(name);
        return card;
    }

    private IndianBankInfo findAadhaarByCard(@NonNull IndianBankInfo card) {
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

    private IndianBankInfo findBankCardByCard(@NonNull IndianBankInfo card) {
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
