package study.daydayup.wolf.common.lang.contract.ability;

import study.daydayup.wolf.common.lang.contract.worker.Visitor;

/**
 * study.daydayup.wolf.common.lang.contract
 *
 * @author Wingle
 * @since 2020/12/2 12:01 下午
 **/
public interface Visitable {
     void accept(Visitor<? extends Visitable> visitor);
}
