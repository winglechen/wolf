package study.daydayup.wolf.common.util.collection.joiner;

import lombok.Setter;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * study.daydayup.wolf.common.util.collection
 * Collection joiner gateway
 * @author Wingle
 * @since 2020/5/18 3:01 下午
 **/
public class CollectionJoiner<BASE, EXT> {
    @Setter
    private Joiner<BASE, EXT> joiner;

    public static <BASE, EXT> Joiner<BASE, EXT> base(Collection<BASE> base) {
        CollectionJoiner<BASE, EXT> gateway = new CollectionJoiner<>();
        Joiner<BASE, EXT> joiner = new DefaultJoiner<>(base, gateway);

        gateway.setJoiner(joiner);
        return joiner;
    }

    @SafeVarargs
    public final Joiner<BASE, EXT> on(BiConsumer<BASE, EXT> setter, Function<BASE, Object>... getters) {
        Collection<BASE> base = joiner.getBaseList();
        Joiner<BASE, EXT> joiner = new DefaultJoiner<>(base, this);

        setJoiner(joiner);
        joiner.on(setter, getters);
        return joiner;
    }

    public Collection<BASE> getList() {
        if (joiner == null) {
            return null;
        }
        return joiner.getBaseList();
    }

}
