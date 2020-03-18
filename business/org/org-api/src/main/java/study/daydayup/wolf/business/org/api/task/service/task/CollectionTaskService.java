package study.daydayup.wolf.business.org.api.task.service.task;

import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.org.api.task.service.task
 *
 * @author Wingle
 * @since 2020/3/15 11:24 下午
 **/
public interface CollectionTaskService extends Service {
    Result<Integer> partlyPay(Long taskId, Long orgId, BigDecimal amount);
    Result<Integer> confirmPartlyPay(Long taskId, Long orgId, BigDecimal amount);
    Result<Integer> pay(Long taskId, Long orgId);
    Result<Integer> confirmPay(Long taskId, Long orgId);
    Result<Integer> fail(Long taskId, Long orgId);
    Result<Integer> confirmFail(Long taskId, Long orgId);
}
