package study.daydayup.wolf.business.uc.crm.biz.customer.info.api.india;

import lombok.NonNull;
import study.daydayup.wolf.business.uc.api.crm.customer.info.dto.india.IndianPayInfo;
import study.daydayup.wolf.business.uc.api.crm.customer.info.service.india.IndianCustomerService;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao.AadhaarDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao.BankCardDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao.BasicInfoDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao.UserDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.AadhaarDO;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.BankCardDO;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.BasicInfoDO;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.UserDO;
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
    @Resource
    private UserDAO userDAO;

    @Override
    public Result<IndianPayInfo> findIndianBankCard(Long accountId, Long orgId) {
        IndianPayInfo card = IndianPayInfo.builder()
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
    public Result<IndianPayInfo> findIndianAadhaar(Long accountId, Long orgId) {
        IndianPayInfo card = IndianPayInfo.builder()
                .accountId(accountId)
                .orgId(orgId)
                .build();

        card = findBasicInfoByCard(card);
        if (card == null) {
            return Result.fail(10000, "no such card");
        }
        return Result.ok(card);
    }

    @Override
    public Result<IndianPayInfo> findIndianContact(Long accountId, Long orgId) {
        IndianPayInfo card = IndianPayInfo.builder()
                .accountId(accountId)
                .orgId(orgId)
                .build();

        card = findUserInfoByCard(card);
        if (card == null) {
            return Result.fail(10000, "no such card");
        }
        return Result.ok(card);
    }

    private IndianPayInfo findUserInfoByCard(@NonNull IndianPayInfo card) {
        UserDO userDO = userDAO.selectByAccountIdAndOrgId(card.getAccountId(), card.getOrgId());
        if (userDO == null
                || null == userDO.getRealName()) {
            return null;
        }

        card.setAadhaarNo(userDO.getAadhaarNo());
        card.setAadhaarName(userDO.getRealName());
        card.setMobile(userDO.getMobile());
        card.setEmail(userDO.getEmail());

        return card;
    }

    private IndianPayInfo findBasicInfoByCard(@NonNull IndianPayInfo card) {
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
        card.setMobile("");
        return card;
    }

    private IndianPayInfo findAadhaarByCard(@NonNull IndianPayInfo card) {
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

    private IndianPayInfo findBankCardByCard(@NonNull IndianPayInfo card) {
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
