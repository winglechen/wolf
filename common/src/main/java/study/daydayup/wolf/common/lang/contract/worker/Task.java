package study.daydayup.wolf.common.lang.contract.worker;

import study.daydayup.wolf.common.lang.contract.ability.Executable;
import study.daydayup.wolf.common.lang.contract.ability.Retryable;

/**
 * study.daydayup.wolf.common.lang.contract.worker
 *
 * @author Wingle
 * @since 2021/11/19 上午12:48
 **/
public interface Task extends Executable, Retryable {
}
