package com.wolf.framework.layer.monitor.trade;

import lombok.Data;
import com.wolf.framework.layer.domain.VO;

import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * com.wolf.framework.layer.monitor.trade
 *
 * @author Wingle
 * @since 2021/11/15 下午9:56
 **/
@Data
public class LatestTrade implements VO {
    String name;
    LocalTime time;

    int count;
    BigDecimal amount;
}
