package com.onedot.win.common.lang.contract.worker;

import com.onedot.win.common.lang.contract.ability.Executable;
import com.onedot.win.common.lang.contract.ability.Retryable;

/**
 * com.onedot.win.common.lang.contract.worker
 *
 * @author Wingle
 * @since 2021/11/19 上午12:48
 **/
public interface Task extends Executable, Retryable {
}
