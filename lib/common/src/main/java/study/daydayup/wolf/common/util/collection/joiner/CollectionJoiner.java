package study.daydayup.wolf.common.util.collection.joiner;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * study.daydayup.wolf.common.util.collection
 * Collection joiner gateway
 * @author Wingle
 * @since 2020/5/18 3:01 下午
 **/
public class CollectionJoiner {
    @SafeVarargs
    public static <BASE, EXT> Joiner<BASE, EXT> join(Collection<BASE> base, Function<BASE, Object>...getters) {
        return new DefaultJoiner<BASE, EXT>();
    }

    @SafeVarargs
    public final <BASE, EXT> Joiner<BASE, EXT> join(Collection<EXT> ext, BiConsumer<BASE, EXT> setter, Function<EXT, Object>... getters) {
        return null;
    }
}
