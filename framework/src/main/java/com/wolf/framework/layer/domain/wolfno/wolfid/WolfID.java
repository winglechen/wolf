package com.wolf.framework.layer.domain.wolfno.wolfid;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;

@Data
public class WolfID implements Serializable {
    private boolean hasStandBy = false;

    private String name;
    private int step;

    private AtomicInteger currentID;
    private int maxID;
    private double rate;

    private long createTime;
}
