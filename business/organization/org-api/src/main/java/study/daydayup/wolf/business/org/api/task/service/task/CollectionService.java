package study.daydayup.wolf.business.org.api.task.service.task;

import study.daydayup.wolf.business.org.api.task.domain.entity.Task;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.org.api.task.service.task
 *
 * @author Wingle
 * @since 2020/3/15 11:24 下午
 **/
public interface CollectionService extends Service {
    Result<Task> findCollection(Long taskId, Long orgId);
    Result<Task> findContact(Long taskId, Long orgId);
    Result<Page<Task>> findContactsByCollection(Long taskId, Long orgId, PageRequest pageRequest);

    Result<Integer> partlyPay(Long taskId, Long orgId, BigDecimal amount);
    Result<Integer> confirmPartlyPay(Long taskId, Long orgId, BigDecimal amount);
    Result<Integer> pay(Long taskId, Long orgId);
    Result<Integer> confirmPay(Long taskId, Long orgId);
    Result<Integer> failToPay(Long taskId, Long orgId);
    Result<Integer> confirmAsLoss(Long taskId, Long orgId);
}
