package study.daydayup.wolf.common.sm;

import org.junit.Test;

import java.util.Set;

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
        assertEquals("InitState failed", expected.getClass().getName(), initState.getClass().getName());
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

        stateMachine.bind(init, paid, payEvent);
        stateMachine.bind(paid, consigned, sendEvent);

        TradeState expectedPaid = stateMachine.fire(init, payEvent);
        assertEquals("paid event bind fail", expectedPaid, paid);

        TradeState expectedConsigned = stateMachine.fire(paid, sendEvent);
        assertEquals("send event bind fail", expectedConsigned, consigned);
    }

    @Test
    public void test_different_event_instance() {
        StateMachine<TradeState, TradeEvent> stateMachine = new DefaultStateMachine<>();

        TradeState paid = new Paid();
        TradeState consigned = new Consigned();
        TradeEvent sendEvent = new SendEvent();

        stateMachine.bind(paid, consigned, sendEvent);

        TradeState expectedState = stateMachine.fire(paid, new SendEvent());
        assertEquals("different event instance fail.", expectedState, consigned);

    }

    @Test
    public void test_different_state_instance() {
        StateMachine<TradeState, TradeEvent> stateMachine = new DefaultStateMachine<>();

        TradeState paid = new Paid();
        TradeState consigned = new Consigned();
        TradeEvent sendEvent = new SendEvent();

        stateMachine.bind(paid, consigned, sendEvent);

        TradeState expectedState = stateMachine.fire(new Paid(), new SendEvent());
        assertEquals("different source instance fail.", expectedState, consigned);
    }

    @Test
    public void test_get_state_by_code() {
        StateMachine<TradeState, TradeEvent> stateMachine = new DefaultStateMachine<>();

        TradeState paid = new Paid();
        TradeState consigned = new Consigned();
        TradeEvent sendEvent = new SendEvent();

        stateMachine.bind(paid, consigned, sendEvent);

        TradeState paidFromSM = stateMachine.getStateByCode(2);
        TradeState consignFromSM = stateMachine.getStateByCode(3);
        assertEquals("get state by code from StateMachine fail.", consignFromSM, consigned);
        assertEquals("get state by code from StateMachine fail.", paid, paidFromSM);
    }

    @Test
    public void test_get_bind_event_list() {
        StateMachine<TradeState, TradeEvent> stateMachine = new DefaultStateMachine<>();

        TradeState paid = new Paid();
        TradeState consigned = new Consigned();
        TradeEvent sendEvent = new SendEvent();

        stateMachine.bind(paid, consigned, sendEvent);

        Set<TradeEvent> events = stateMachine.getBindEventList(paid);
        assertTrue("get bind event list fail", events.contains(sendEvent));
    }



    interface TradeState extends State { }

    interface TradeEvent extends Event { }

    static class WaitToPay implements TradeState {
        @Override
        public int getCode() {
            return 1;
        }
    }

    static class Paid implements TradeState {
        @Override
        public int getCode() {
            return 2;
        }
    }

    static class Consigned implements TradeState {
        @Override
        public int getCode() {
            return 3;
        }
    }

    static class PayEvent implements TradeEvent {}
    static class SendEvent implements TradeEvent {}

}