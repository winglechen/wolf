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
    Joiner<BASE, EXT> on(BiConsumer<BASE, EXT> setter, Function<BASE, Object> ...getters);

    @SuppressWarnings("all")
    Joiner<BASE, EXT> join(Collection<EXT> ext, Function<EXT, Object> ...getters);

    Collection<BASE> getList();
}
