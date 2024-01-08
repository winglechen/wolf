package study.daydayup.wolf.common.sm.builder;

import study.daydayup.wolf.common.lang.contract.Context;
import study.daydayup.wolf.common.sm.Event;
import study.daydayup.wolf.common.sm.State;
import study.daydayup.wolf.common.sm.StateMachine;
import study.daydayup.wolf.common.sm.Transition;

/**
 * study.daydayup.wolf.common.sm.builder
 *
 * @author Wingle
 * @since 2020/12/2 9:46 上午
 **/
public interface StateMachineBuilder<S extends State, E extends Event, C extends Context> {
    TransitionBuilder<S, E, C> addTransition();

    TransitionBuilder<S, E, C> source(S state);

    TransitionBuilder<S, E, C> target(S state);

    TransitionBuilder<S, E, C> event(E event);

    void addTransition(Transition<S, E, C> transition);

    StateMachine<S, E, C> build();
}
