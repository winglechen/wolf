package com.onedot.win.common.sm.impl;

import com.onedot.win.common.lang.contract.Context;
import com.onedot.win.common.lang.contract.worker.Visitor;
import com.onedot.win.common.sm.Event;
import com.onedot.win.common.sm.State;
import com.onedot.win.common.sm.StateMachine;
import com.onedot.win.common.sm.Transition;
import com.onedot.win.common.sm.builder.DefaultStateMachineBuilder;
import com.onedot.win.common.sm.builder.StateMachineBuilder;

import java.util.Set;

/**
 * com.onedot.win.common.sm.builder
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
