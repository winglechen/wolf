package study.daydayup.wolf.common.lang.contract.collection;

import java.util.Collection;

/**
 * study.daydayup.wolf.common.lang.contract.collection
 *
 * @author Wingle
 * @since 2021/11/15 下午9:27
 **/
public interface Queue<T> {
    boolean isEmpty();

    void enqueue(T t);
    T dequeue();
    Collection<T> dequeue(int n);
}
