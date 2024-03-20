package com.onedot.win.framework.layer.monitor.trade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.common.util.time.DateUtil;
import com.onedot.win.framework.layer.domain.VO;
import com.onedot.win.framework.layer.monitor.alert.AlertLevelEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * com.onedot.win.framework.layer.monitor.trade
 *
 * @author Wingle
 * @since 2022/2/22 下午10:27
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeAlert implements VO {
    private AlertLevelEnum level;
    private String name;
    private Integer lowMinutes;
    private Integer highMinutes;
    private Double rate;
    private List<BigDecimal> rateList;

    @Override
    public String toString() {
        if (lowMinutes == null) {
            lowMinutes = 1;
        }

        if (highMinutes == null) {
            highMinutes = 1;
        }

        if (rate == null) {
            rate = 0.0;
        }

        rate = rate * 100;

        return StringUtil.joinWith(" ",
                DateUtil.asString(LocalDateTime.now()),
                name, "lower than",
                rate + "%",
                "for", lowMinutes, "minutes",
                "higher rate for", highMinutes, "minutes",
                "rateList:", rateList
        );
    }
}
