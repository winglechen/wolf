package study.daydayup.wolf.business.trade.tm.biz.engine;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.ContractRequest;
import study.daydayup.wolf.business.trade.tm.biz.engine.core.AbstractEngine;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;

/**
 * study.daydayup.wolf.business.trade.tm.biz.engine
 *
 * @author Wingle
 * @since 2020/1/10 12:26 下午
 **/
@Component
public class ContractQueryEngine extends AbstractEngine<ContractRequest> {
    public Result<Page<Contract>> query(ContractRequest request) {
        init(request);

        return null;
    }


}
