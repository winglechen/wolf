package com.onedot.win.common.lang.contract.ability;

import com.onedot.win.common.lang.contract.worker.Visitor;

/**
 * com.onedot.win.common.lang.contract
 *
 * @author Wingle
 * @since 2020/12/2 12:01 下午
 **/
public interface Visitable {
     void accept(Visitor<? extends Visitable> visitor);
}
