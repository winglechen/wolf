package study.daydayup.wolf.common.sm;

import study.daydayup.wolf.common.lang.contract.Context;
import study.daydayup.wolf.common.lang.contract.worker.Visitor;

import java.util.Set;

/**
 * study.daydayup.wolf.common.sm
 *
 * @author Wingle
 * @since 2020/12/2 9:29 上午
 **/
public interface StateMachine<S extends State, E extends Event, C extends Context> {
    S getRootState();

    Set<Transition<S, E, C>> getTransitions(S state);

    Set<Transition<S, E, C>> getTransitions(int code);

    StateMachine<S, E, C> addTransition(Transition<S, E, C> transition);

    S transit(S source, E event, C context);

    void accept(Visitor<StateMachine<S, E, C>> visitor);
}
