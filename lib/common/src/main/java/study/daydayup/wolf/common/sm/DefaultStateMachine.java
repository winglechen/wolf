package study.daydayup.wolf.common.sm;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * study.daydayup.wolf.common.sm
 *
 * @author Wingle
 * @since 2019/12/16 10:32 下午
 **/
public class DefaultStateMachine<S extends State, E extends Event> implements StateMachine<S, E> {
    private S initState;
    private Map<String, Map<String, S>> data;
    private Map<Integer, S> stateCodeMap;

    public DefaultStateMachine() {
        this(null);
    }

    public DefaultStateMachine(S state) {
        data = new HashMap<>();
        stateCodeMap = new HashMap<>();
        init(state);
    }

    @Override
    public DefaultStateMachine<S, E> init(S state) {
        if (state != null) {
            this.initState = state;
            registerState(state);
        }

        return this;
    }

    @Override
    public S getInitState() {
        return initState;
    }

    @Override
    public StateMachine<S, E> add(S source, S target, E event) {
        registerStates(source, target);

        String sourceKey = source.getClass().getSimpleName();
        String eventKey  = event.getClass().getSimpleName();

        Map<String, S> stateMap = this.data.get(sourceKey);
        if (stateMap == null) {
            createStateMap(sourceKey, eventKey, target);
            return this;
        }
        addStateToMap(stateMap, sourceKey, eventKey, target);

        return this;
    }

    @Override
    public S fire(S source, E event) {
        String sourceKey = source.getClass().getSimpleName();
        String eventKey = event.getClass().getSimpleName();

        Map<String, S> stateMap = this.data.get(sourceKey);
        if (stateMap == null) {
            return null;
        }

        return stateMap.get(eventKey);
    }

    @Override
    public S getStateByCode(int code) {
        return stateCodeMap.get(code);
    }

    @SafeVarargs
    private final void registerStates(S... states) {
        for (S state: states ) {
            registerState(state);
        }
    }

    private void registerState(S state) {
        int code = state.getCode();

        if (stateCodeMap.containsKey(code)) {
            return;
        }
        stateCodeMap.put(code, state);
    }

    private void createStateMap(String sourceKey, String eventKey, S target) {
        Map<String, S> stateMap = new TreeMap<>();
        stateMap.put(eventKey, target);

        data.put(sourceKey, stateMap);
    }

    private void addStateToMap(Map<String, S> stateMap, String sourceKey, String eventKey, S target) {
        S state = stateMap.get(eventKey);

        if (state != null) {
            String targetKey = target.getClass().getSimpleName();
            throw new DuplicateStateMapException(sourceKey, targetKey, eventKey);
        }

        stateMap.put(eventKey, target);
    }

}
