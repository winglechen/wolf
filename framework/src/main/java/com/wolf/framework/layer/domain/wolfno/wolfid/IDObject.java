package com.wolf.framework.layer.domain.wolfno.wolfid;

import java.io.Serializable;
import lombok.Data;

@Data
public class IDObject implements Serializable {
    /**
     * 1千万
     */
    public final static int DEFAULT_VALUE = 1111111;
    /**
     *
     */
    public final static int DEFAULT_STEP = 1000;

    /**
     *
     */
    public final static double DEFAULT_RATE = 0.6;

    private String name;
    private int step;

    private int currentValue;
    private int maxValue;
    private double rateToFetch;

    private long lastUpdateTime;
}
