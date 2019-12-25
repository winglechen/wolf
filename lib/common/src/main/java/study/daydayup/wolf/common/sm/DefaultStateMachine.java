package study.daydayup.wolf.common.sm;


import java.util.*;

/**
 * study.daydayup.wolf.common.sm
 *
 * @author Wingle
 * @since 2019/12/16 10:32 下午
 **/
public class DefaultStateMachine<S extends State, E extends Event> implements StateMachine<S, E> {
    private S initState;
    private Map<String, Map<String, S>> stateTree;
    private Map<String, Set<E>> eventMap;
    private Map<Integer, S> stateCodeMap;

    public DefaultStateMachine() {
        this(null);
    }

    public DefaultStateMachine(S state) {
        stateTree = new HashMap<>();
        stateCodeMap = new HashMap<>();
        eventMap = new HashMap<>();

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
    public StateMachine<S, E> bind(S source, S target, E event) {
        registerStates(source, target);

        String sourceKey = source.getClass().getName();

        Map<String, S> stateMap = this.stateTree.get(sourceKey);
        if (stateMap == null) {
            createStateMap(sourceKey, event, target);
            return this;
        }
        addStateToMap(stateMap, sourceKey, event, target);

        return this;
    }

    @Override
    public S fire(S source, E event) {
        String sourceKey = source.getClass().getName();
        String eventKey = event.getClass().getName();

        Map<String, S> stateMap = this.stateTree.get(sourceKey);
        if (stateMap == null) {
            return null;
        }

        return stateMap.get(eventKey);
    }

    @Override
    public Set<E> getBindEventList(S source) {
        String sourceKey = source.getClass().getName();
        return eventMap.get(sourceKey);
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

    private void createStateMap(String sourceKey, E event, S target) {
        String eventKey  = event.getClass().getName();

        Map<String, S> stateMap = new TreeMap<>();
        stateMap.put(eventKey, target);
        stateTree.put(sourceKey, stateMap);

        Set<E> eventSet = new HashSet<>();
        eventSet.add(event);
        eventMap.put(sourceKey, eventSet);

    }

    private void addStateToMap(Map<String, S> stateMap, String sourceKey, E event, S target) {
        String eventKey  = event.getClass().getName();
        S state = stateMap.get(eventKey);

        if (state != null) {
            String targetKey = target.getClass().getName();
            throw new DuplicateStateMapException(sourceKey, targetKey, eventKey);
        }

        stateMap.put(eventKey, target);
    }

}
