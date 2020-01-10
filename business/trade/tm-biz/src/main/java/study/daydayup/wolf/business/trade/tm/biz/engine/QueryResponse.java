package study.daydayup.wolf.business.trade.tm.biz.engine;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.framework.layer.api.Response;
import study.daydayup.wolf.framework.rpc.page.Page;

/**
 * study.daydayup.wolf.business.trade.tm.biz.engine
 *
 * @author Wingle
 * @since 2020/1/10 1:10 下午
 **/
@Data
public class QueryResponse implements Response {
    private Page<Contract> contracts;
    private Page<Order> orders;
}
