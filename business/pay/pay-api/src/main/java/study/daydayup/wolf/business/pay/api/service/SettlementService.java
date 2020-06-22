package study.daydayup.wolf.business.pay.api.service;

import study.daydayup.wolf.business.pay.api.domain.entity.Settlement;
import study.daydayup.wolf.business.pay.api.dto.base.manage.SettlementQuery;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

/**
 * study.daydayup.wolf.business.pay.api.service
 *
 * @author Wingle
 * @since 2020/6/21 5:36 下午
 **/
public interface SettlementService extends Service {
    Result<Page<Settlement>> query(SettlementQuery query, PageRequest pageRequest);
}
