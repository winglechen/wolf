package study.daydayup.wolf.common.sm.builder;

import study.daydayup.wolf.common.lang.contract.Context;
import study.daydayup.wolf.common.sm.Event;
import study.daydayup.wolf.common.sm.State;
import study.daydayup.wolf.common.sm.StateMachine;
import study.daydayup.wolf.common.sm.Transition;
import study.daydayup.wolf.common.sm.impl.DefaultStateMachine;

/**
 * study.daydayup.wolf.common.sm.builder
 *
 * @author Wingle
 * @since 2020/12/2 9:46 上午
 **/
public class DefaultStateMachineBuilder<S extends State, E extends Event, C extends Context> implements StateMachineBuilder<S, E, C> {

    public TransitionBuilder<S, E, C> addTransition() {
        return new TransitionBuilder<>(this);
    }

    @Override
    public TransitionBuilder<S, E, C> source(S state) {
        return new TransitionBuilder<>(this);
    }

    @Override
    public TransitionBuilder<S, E, C> target(S state) {
        return new TransitionBuilder<>(this);
    }

    @Override
    public TransitionBuilder<S, E, C> event(E event) {
        return new TransitionBuilder<>(this);
    }

    @Override
    public void addTransition(Transition<S, E, C> transition) {

    }

    public StateMachine<S, E, C> build() {
        return new DefaultStateMachine<>();
    }
}
