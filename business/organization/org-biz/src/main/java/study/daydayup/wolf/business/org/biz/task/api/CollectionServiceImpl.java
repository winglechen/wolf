package study.daydayup.wolf.business.org.biz.task.api;

import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.business.org.api.task.service.task.CollectionService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.org.biz.task.api
 *
 * @author Wingle
 * @since 2020/3/16 12:44 上午
 **/
@RpcService(protocol = "dubbo")
public class CollectionServiceImpl implements CollectionService {
    @Override
    public Result<Task> findCollection(Long taskId, Long orgId) {
        return null;
    }

    @Override
    public Result<Task> findContact(Long taskId, Long orgId) {
        return null;
    }

    @Override
    public Result<Page<Task>> findContactsByCollection(Long taskId, Long orgId, PageRequest pageRequest) {
        return null;
    }

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
    public Result<Integer> failToPay(Long taskId, Long orgId) {
        return null;
    }

    @Override
    public Result<Integer> confirmAsLoss(Long taskId, Long orgId) {
        return null;
    }
}
