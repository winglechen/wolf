package com.wolf.framework.layer.domain.uid;

import java.io.Serializable;
import lombok.Data;

@Data
public class IDObject implements Serializable {
    private String name;
    private int step;

    private int currentValue;
    private int maxValue;

    private long lastUpdateTime;
}
