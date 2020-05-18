package study.daydayup.wolf.common.util.collection.joiner;

import lombok.Getter;
import study.daydayup.wolf.common.util.collection.joiner.exception.InvalidGetterException;

import java.util.Collection;
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
    private static final int MAX_GETTER_LENGTH = 0;


    @Getter
    private Collection<BASE> base;
    @Getter
    private Function<BASE, Object>[] baseGetters;

    private Map<Object, EXT> extMap;

    @SafeVarargs
    @Override
    public final Joiner<BASE, EXT> base(Collection<BASE> base, Function<BASE, Object>... getters) {
        validBaseGetter(getters);

        this.base = base;
        this.baseGetters = getters;

        return this;
    }

    @SafeVarargs
    @Override
    public final Joiner<BASE, EXT> join(Collection<EXT> ext, BiConsumer<BASE, EXT> setter, Function<EXT, Object>... getters) {
        validExtGetter(getters);

        return this;
    }

    @SafeVarargs
    private final void validExtGetter(Function<EXT, Object>... getters) {
        int len = getters.length;
        if (len < MIN_GETTER_LENGTH || len > MIN_GETTER_LENGTH) {
            throw new InvalidGetterException(len);
        }
    }

    @SafeVarargs
    private final void validBaseGetter(Function<BASE, Object>... getters) {
        int len = getters.length;
        if (len < MIN_GETTER_LENGTH || len > MIN_GETTER_LENGTH) {
            throw new InvalidGetterException(len);
        }
    }
}
