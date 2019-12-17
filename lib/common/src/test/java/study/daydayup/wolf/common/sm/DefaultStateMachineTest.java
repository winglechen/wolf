package study.daydayup.wolf.common.sm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.common.sm
 *
 * @author Wingle
 * @since 2019/12/17 11:23 上午
 **/
public class DefaultStateMachineTest {

    @Test
    public void getInitState() {
        StateMachine<TradeState, TradeEvent> stateMachine = new DefaultStateMachine<>(new WaitToPay());

        TradeState initState = stateMachine.getInitState();
        TradeState expected  = new WaitToPay();
        assertEquals("InitState failed", expected.getClass().getSimpleName(), initState.getClass().getSimpleName());
    }

    @Test
    public void fire() {
        TradeState init = new WaitToPay();
        TradeState paid = new Paid();
        TradeState consigned = new Consigned();

        TradeEvent payEvent = new PayEvent();
        TradeEvent sendEvent = new SendEvent();

        StateMachine<TradeState, TradeEvent> stateMachine = new DefaultStateMachine<>(init);

        assertEquals("init state fail", init, stateMachine.getInitState());

        stateMachine.add(init, paid, payEvent);
        stateMachine.add(paid, consigned, sendEvent);

        TradeState expectedPaid = stateMachine.fire(init, payEvent);
        assertEquals("paid event bind fail", expectedPaid, paid);

        TradeState expectedConsigned = stateMachine.fire(paid, sendEvent);
        assertEquals("send event bind fail", expectedConsigned, consigned);
    }


    interface TradeState { }

    interface TradeEvent { }

    static class WaitToPay implements TradeState {}

    static class Paid implements TradeState {}

    static class Consigned implements TradeState {}

    static class PayEvent implements TradeEvent {}
    static class SendEvent implements TradeEvent {}

}