package com.onedot.win.framework.layer.monitor.trade;

import lombok.Data;
import com.onedot.win.framework.layer.domain.VO;

import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * com.onedot.win.framework.layer.monitor.trade
 *
 * demo list [
 *      {"09:10", 1000, 600, 100, 0.6, 0.1, }
 * ]
 * @author Wingle
 * @since 2021/11/15 下午9:56
 **/
@Data
public class TradeRate implements VO {
    String name;
    LocalTime time;

    int totalNum;

    // success state
    int successNum;
    // failure state
    int failureNum;
    // tagged state count (demo: payout wait to transfer)
    int taggedNum;
    // abnormal state count (demo: payout reversed)
    int abnormalNum;

    BigDecimal totalAmount;
    BigDecimal successAmount;
    BigDecimal failureAmount;
    BigDecimal taggedAmount;
    BigDecimal abnormalAmount;

    BigDecimal successRate;
    BigDecimal failureRate;
    BigDecimal taggedRate;
    BigDecimal abnormalRate;
}
