package study.daydayup.wolf.common.sm;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * study.daydayup.wolf.common.sm
 *
 * @author Wingle
 * @since 2019/12/16 10:32 下午
 **/
public class DefaultStateMachine<State, Event> implements StateMachine<State, Event> {
    private State initState;
    private Map<String, Map<String, State>> data;

    public DefaultStateMachine() {
        this(null);
    }

    public DefaultStateMachine(State state) {
        if (state != null) {
            this.initState = state;
        }

        data = new HashMap<>();
//        data = new ConcurrentHashMap<>();
    }

    @Override
    public DefaultStateMachine<State, Event> init(State state) {
        this.initState = state;

        return this;
    }

    @Override
    public State getInitState() {
        return initState;
    }

    @Override
    public DefaultStateMachine<State, Event> add(State source, State target, Event event) {
        String sourceKey = source.getClass().getSimpleName();
        String eventKey = event.getClass().getSimpleName();
        String targetKey = target.getClass().getSimpleName();

        Map<String, State> stateMap = this.data.get(sourceKey);
        if (stateMap == null) {
            stateMap = new HashMap<>();
            stateMap.put(eventKey, target);

            this.data.put(sourceKey, stateMap);
            return this;
        }

        State state = stateMap.get(eventKey);
        if (state != null) {
            throw new DuplicateStateMapException(sourceKey, targetKey, eventKey);
        }

        stateMap.put(eventKey, target);
        return this;
    }

    @Override
    public State fire(State source, Event event) {
        String sourceKey = source.getClass().getSimpleName();
        String eventKey = event.getClass().getSimpleName();

        Map<String, State> stateMap = this.data.get(sourceKey);
        if (stateMap == null) {
            return null;
        }

        return stateMap.get(eventKey);
    }
}
