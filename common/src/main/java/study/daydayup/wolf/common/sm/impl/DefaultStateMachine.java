package study.daydayup.wolf.common.sm.impl;

import study.daydayup.wolf.common.lang.contract.Context;
import study.daydayup.wolf.common.lang.contract.worker.Visitor;
import study.daydayup.wolf.common.sm.Event;
import study.daydayup.wolf.common.sm.State;
import study.daydayup.wolf.common.sm.StateMachine;
import study.daydayup.wolf.common.sm.Transition;
import study.daydayup.wolf.common.sm.builder.DefaultStateMachineBuilder;
import study.daydayup.wolf.common.sm.builder.StateMachineBuilder;

import java.util.Set;

/**
 * study.daydayup.wolf.common.sm.builder
 *
 * @author Wingle
 * @since 2020/12/2 9:46 上午
 **/
public class DefaultStateMachine<S extends State, E extends Event, C extends Context> implements StateMachine<S, E, C> {

    public static <S extends State, E extends Event, C extends Context> StateMachineBuilder<S, E, C> builder() {
        return new DefaultStateMachineBuilder<>();
    }


    @Override
    public S getRootState() {
        return null;
    }

    @Override
    public Set<Transition<S, E, C>> getTransitions(S state) {
        return null;
    }

    @Override
    public Set<Transition<S, E, C>> getTransitions(int code) {
        return null;
    }

    @Override
    public StateMachine<S, E, C> addTransition(Transition<S, E, C> transition) {
        return null;
    }

    @Override
    public S transit(S source, E event, C context) {
        return null;
    }

    @Override
    public void accept(Visitor<StateMachine<S, E, C>> visitor) {

    }


}
