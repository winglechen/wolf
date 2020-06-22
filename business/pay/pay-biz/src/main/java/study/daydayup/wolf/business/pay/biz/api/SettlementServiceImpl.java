package study.daydayup.wolf.business.pay.biz.api;

import study.daydayup.wolf.business.pay.api.domain.entity.Settlement;
import study.daydayup.wolf.business.pay.api.dto.base.manage.SettlementQuery;
import study.daydayup.wolf.business.pay.api.service.SettlementService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

/**
 * study.daydayup.wolf.business.pay.biz.api
 *
 * @author Wingle
 * @since 2020/6/22 10:49 上午
 **/
@RpcService
public class SettlementServiceImpl implements SettlementService {
    @Override
    public Result<Page<Settlement>> query(SettlementQuery query, PageRequest pageRequest) {
        return null;
    }
}
