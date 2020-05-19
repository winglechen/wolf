package study.daydayup.wolf.common.util.collection.joiner;

import lombok.Getter;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.collection.joiner.exception.InvalidGetterException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * study.daydayup.wolf.common.util.collection.joiner
 *
 * @author Wingle
 * @since 2020/5/18 3:13 下午
 **/
public class DefaultJoiner<BASE, EXT> implements Joiner<BASE, EXT> {
    private static final int MIN_GETTER_LENGTH = 1;
    private static final int MAX_GETTER_LENGTH = 10;
    private static final String NULL_VALUE = "NULL_VALUE";
    private static final String KEY_DELIMITER = ":";

    @Getter
    private Collection<BASE> baseList;
    private Function<BASE, Object>[] baseGetters;
    private BiConsumer<BASE, EXT> baseSetter;

    private Map<Object, EXT> extMap;

    public static <BASE, EXT> Joiner<BASE, EXT> base(Collection<BASE> base) {
        return new DefaultJoiner<>(base);
    }

    public DefaultJoiner(Collection<BASE> baseList) {
        this.baseList = baseList;
    }

    @SafeVarargs
    @Override
    public final Joiner<BASE, EXT> on(BiConsumer<BASE, EXT> setter, Function<BASE, Object>... getters) {
        validBaseGetter(getters);

        baseSetter = setter;
        baseGetters = getters;

        return this;
    }

    @SafeVarargs
    @Override
    public final Joiner<BASE, EXT> join(Collection<EXT> extList, Function<EXT, Object>... getters) {
        if (CollectionUtil.isEmpty(extList)) {
            return this;
        }

        validExtGetter(getters);
        formatExtMap(extList, getters);
        joinExtMap();

        return this;
    }

    @Override
    public Collection<BASE> getList() {
        return this.baseList;
    }

    private void joinExtMap() {
        if (CollectionUtil.isEmpty(baseList)) {
            return;
        }

        Object key;
        EXT ext;
        for (BASE base : baseList) {
            key = getBaseKey(base);
            ext = extMap.get(key);
            if (ext == null) {
                continue;
            }
            baseSetter.accept(base, ext);
        }
    }

    @SafeVarargs
    private final void formatExtMap(Collection<EXT> extList, Function<EXT, Object>... getters) {
        extMap = new HashMap<>(extList.size());

        Object key;
        for (EXT ext : extList) {
            key = getExtKey(ext, getters);
            extMap.put(key, ext);
        }
    }

    @SafeVarargs
    private final void validExtGetter(Function<EXT, Object>... getters) {
        int len = getters.length;
        if (len < MIN_GETTER_LENGTH || len > MAX_GETTER_LENGTH) {
            throw new InvalidGetterException(len);
        }
    }

    @SafeVarargs
    private final void validBaseGetter(Function<BASE, Object>... getters) {
        int len = getters.length;
        if (len < MIN_GETTER_LENGTH || len > MAX_GETTER_LENGTH) {
            throw new InvalidGetterException(len);
        }
    }

    private Object getBaseKey(BASE base) {
        Object value;
        if (1 == baseGetters.length) {
            value = baseGetters[0].apply(base);
            return formatValue(value);
        }

        StringBuilder sb = new StringBuilder();
        for (Function<BASE, Object> getter : baseGetters) {
            value = getter.apply(base);
            sb.append(value).append(KEY_DELIMITER);
        }

        return sb.toString();
    }

    private Object getExtKey(EXT ext, Function<EXT, Object>... getters) {
        Object value;
        if (1 == getters.length) {
            value = getters[0].apply(ext);
            return formatValue(value);
        }

        StringBuilder sb = new StringBuilder();
        for (Function<EXT, Object> getter : getters) {
            value = getter.apply(ext);
            sb.append(value).append(KEY_DELIMITER);
        }

        return sb.toString();
    }

    private Object formatValue(Object value) {
        if (value != null) {
            return value;
        }

        return NULL_VALUE;
    }
}
