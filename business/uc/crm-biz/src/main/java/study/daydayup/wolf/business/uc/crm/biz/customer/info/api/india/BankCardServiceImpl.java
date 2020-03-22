package study.daydayup.wolf.business.uc.crm.biz.customer.info.api.india;

import lombok.NonNull;
import study.daydayup.wolf.business.uc.api.crm.customer.info.entity.india.BankCard;
import study.daydayup.wolf.business.uc.api.crm.customer.info.service.india.BankCardService;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.converter.india.BankCardConverter;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao.BankCardDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.BankCardDO;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.info.api.india
 *
 * @author Wingle
 * @since 2020/3/22 9:12 下午
 **/
@RpcService(protocol = "dubbo")
public class BankCardServiceImpl implements BankCardService {
    @Resource
    private BankCardDAO dao;

    @Override
    public Result<BankCard> find(@NonNull Long accountId, @NonNull Long orgId) {
        BankCardDO cardDO = dao.selectByAccountIdAndOrgId(accountId, orgId);
        BankCard card = BankCardConverter.toModel(cardDO);
        return Result.ok(card);
    }

    @Override
    public Result<Integer> add(BankCard bankCard) {
        return null;
    }

    @Override
    public Result<Integer> modify(BankCard bankCard) {
        return null;
    }
}
