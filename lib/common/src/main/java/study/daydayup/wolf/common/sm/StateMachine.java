package study.daydayup.wolf.common.sm;

import java.util.Set;

/**
 * study.daydayup.wolf.common.sm
 *
 * @author Wingle
 * @since 2019/12/16 10:20 下午
 **/
public interface StateMachine <S extends State, E extends Event> {
    StateMachine<S, E> init(S state);
    S getInitState();

    StateMachine<S, E> bind(S source, S target, E event);
    S fire(S source,E event);
    Set<E> getBindEventList(S source);

    S getStateByCode(int code);
}
