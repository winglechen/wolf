package com.onedot.win.common.sm.builder;

import com.onedot.win.common.lang.contract.Context;
import com.onedot.win.common.sm.Event;
import com.onedot.win.common.sm.State;
import com.onedot.win.common.sm.StateMachine;
import com.onedot.win.common.sm.Transition;
import com.onedot.win.common.sm.impl.DefaultStateMachine;

/**
 * com.onedot.win.common.sm.builder
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
