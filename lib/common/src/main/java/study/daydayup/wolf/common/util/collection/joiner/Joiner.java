package study.daydayup.wolf.common.util.collection.joiner;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * study.daydayup.wolf.common.util.collection.joiner
 *
 * @author Wingle
 * @since 2020/5/18 3:12 下午
 **/
public interface Joiner<BASE, EXT> {
    @SuppressWarnings("all")
    Joiner<BASE, EXT> base(Collection<BASE> base, Function<BASE, Object> ...getters);
    @SuppressWarnings("all")
    CollectionJoiner join(Collection<EXT> ext, BiConsumer<BASE, EXT> setter, Function<EXT, Object> ...getters);
}
