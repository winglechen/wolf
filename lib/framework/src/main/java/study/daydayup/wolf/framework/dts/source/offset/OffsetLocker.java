package study.daydayup.wolf.framework.dts.source.offset;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * study.daydayup.wolf.framework.layer.dal.scanner
 *
 * @author Wingle
 * @since 2020/2/6 12:22 下午
 **/
public class OffsetLocker {
    private static final Map<String, Lock> lockerMap = new HashMap<>();

    public static boolean lock(@NonNull String key) {
        synchronized (key) {
             return getLock(key).tryLock();
        }
    }

    public static void unlock(@NonNull String key) {
        synchronized (key) {
            Lock lock = getLock(key, false);
            if (lock == null) {
                return;
            }

            lock.unlock();
        }
    }

    private static Lock getLock(@NonNull String key) {
        return getLock(key, true);
    }

    private static Lock getLock(@NonNull String key, boolean createIfNotExists) {
        Lock lock;
        lock = lockerMap.get(key);

        if (lock == null && createIfNotExists) {
            lock = new ReentrantLock();
            lockerMap.put(key, lock);
        }

        return lock;
    }

}
