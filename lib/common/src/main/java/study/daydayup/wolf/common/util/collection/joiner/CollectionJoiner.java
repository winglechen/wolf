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

    public static <BASE, EXT> Joiner<BASE, EXT> base(Collection<BASE> base) {
        return null;
    }

    public <BASE, EXT> Joiner<BASE, EXT> on(BiConsumer<BASE, EXT> setter, Function<BASE, Object> ...getters) {
        return null;
    }

    public <BASE> Collection<BASE> getList() {
        return null;
    }

    public static void main(String[] args) {
//        CollectionJoiner.base(c1)
//                .on(setter, getter1, getter2, ...)
//                .join(c2, getter1, getter2, ...)
//                .on(setter, getter1, getter2, ...)
//                .join(c2, getter1, getter2, ...)
//                .getList();
    }


}
