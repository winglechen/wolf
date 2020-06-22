package study.daydayup.wolf.business.pay.biz.api;

import lombok.NonNull;
import study.daydayup.wolf.business.pay.api.domain.entity.Settlement;
import study.daydayup.wolf.business.pay.api.dto.base.manage.SettlementQuery;
import study.daydayup.wolf.business.pay.api.service.SettlementService;
import study.daydayup.wolf.business.pay.biz.converter.SettlementConverter;
import study.daydayup.wolf.business.pay.biz.dal.dao.SettlementDAO;
import study.daydayup.wolf.business.pay.biz.dal.dataobject.SettlementDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.pay.biz.api
 *
 * @author Wingle
 * @since 2020/6/22 10:49 上午
 **/
@RpcService
public class SettlementServiceImpl implements SettlementService {
    @Resource
    private SettlementDAO settlementDAO;

    @Override
    public Result<Page<Settlement>> query(SettlementQuery query, PageRequest pageRequest) {
        if (StringUtil.notBlank(query.getSettlementNo())) {
            return bySettlementNo(query.getSettlementNo(), query.getAccountId());
        }

        return byRange(query, pageRequest);
    }

    public Result<Page<Settlement>> bySettlementNo(@NonNull String settlementNo, @NonNull Long accountId) {
        SettlementDO settlementDO = settlementDAO.selectBySettlementNoAndAccountId(settlementNo, accountId);
        if (settlementDO == null) {
            return Result.ok(Page.empty());
        }

        Settlement settlement = SettlementConverter.toModel(settlementDO);
        return Result.ok(Page.one(settlement));
    }

    public Result<Page<Settlement>> byRange(SettlementQuery query, PageRequest pageRequest) {
        Page.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<SettlementDO> settlementDOList = settlementDAO.selectByRange(query);
        if (CollectionUtil.isEmpty(settlementDOList)) {
            return Result.ok(Page.empty());
        }

        List<Settlement> settlementList = SettlementConverter.toModel(settlementDOList);
        Page<Settlement> settlementPage =  Page.of(settlementDOList).to(settlementList);
        return Result.ok(settlementPage);
    }

}
