package study.daydayup.wolf.framework.layer.monitor.trade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.common.util.time.DateUtil;
import study.daydayup.wolf.framework.layer.domain.VO;
import study.daydayup.wolf.framework.layer.monitor.alert.AlertLevelEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * study.daydayup.wolf.framework.layer.monitor.trade
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
