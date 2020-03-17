package study.daydayup.wolf.business.org.biz.task.api;

import study.daydayup.wolf.business.org.api.task.service.task.CollectionTaskService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.org.biz.task.api
 *
 * @author Wingle
 * @since 2020/3/16 12:44 上午
 **/
@RpcService(protocol = "dubbo")
public class CollectionTaskServiceImpl implements CollectionTaskService {
    @Override
    public Result<Integer> partlyPay(Long taskId, Long orgId, BigDecimal amount) {
        return null;
    }

    @Override
    public Result<Integer> confirmPartlyPay(Long taskId, Long orgId, BigDecimal amount) {
        return null;
    }

    @Override
    public Result<Integer> pay(Long taskId, Long orgId) {
        return null;
    }

    @Override
    public Result<Integer> confirmPay(Long taskId, Long orgId) {
        return null;
    }

    @Override
    public Result<Integer> fail(Long taskId, Long orgId) {
        return null;
    }

    @Override
    public Result<Integer> confirmAsLoss(Long taskId, Long orgId) {
        return null;
    }
}
