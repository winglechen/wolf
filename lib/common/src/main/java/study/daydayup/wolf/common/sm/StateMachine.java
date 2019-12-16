package study.daydayup.wolf.common.sm;

/**
 * study.daydayup.wolf.common.sm
 *
 * @author Wingle
 * @since 2019/12/16 10:20 下午
 **/
public interface StateMachine <State, Event> {
    StateMachine init(State state);
    State getInitState();
    StateMachine add(State source, State target, Event event);
    State fire(State source,Event event);
}
