package com.wolf.common.util.time;

import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * com.wolf.common.util.time
 *
 * not threadSafe current
 * @author Wingle
 * @since 2022/1/17 下午1:03
 **/
public class Timer {
    private final TimeUnit unit;
    @Getter
    private final Map<String, Long> recordMap;
    private Long start;

    public Timer() {
        unit = TimeUnit.MILLISECONDS;
        recordMap = new HashMap<>();
    }

    public void begin() {
        start = System.currentTimeMillis();
    }

    public void record(@NonNull String name) {
        Long end = System.currentTimeMillis();
        recordMap.put(name, (end-start));

        start = end;
    }

    public Long getRecord(@NonNull String name) {
        return recordMap.get(name);
    }

    public Long elapse() {
        Long end = System.currentTimeMillis();
        return (end - start);
    }

    public void clear() {
        recordMap.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("/*").append("*".repeat(20)).append("*/");

        for (Map.Entry<String, Long> entry : recordMap.entrySet()) {
            sb.append(entry.getKey())
                .append(" elapse: ")
                .append(entry.getValue())
                .append("\n");
        }

        sb.append("/*").append("*".repeat(20)).append("*/");
        return sb.toString();
    }

}
