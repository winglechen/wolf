package study.daydayup.wolf.bigdata.datav.api.dto.daily;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * study.daydayup.wolf.bigdata.datav.api.dto.daily
 *
 * @author Wingle
 * @since 2020/3/26 12:00 上午
 **/
@Data
@Builder
public class TradeDateRequest implements Request {
    @NotNull @Positive
    private Long orgId;

    private Integer tradeType;
    private Integer state;
    private String source;

    private Set<LocalDate> dateList;

    private LocalDate startDate;
    private LocalDate endDate;

    public void add(@NonNull LocalDate date) {
        initDateList();
        dateList.add(date);
    }

    public void addAll(@NonNull Collection<LocalDate> dates) {
        if (dates.isEmpty()) {
            return;
        }
        initDateList();
        dateList.addAll(dates);
    }

    private void initDateList() {
        if (dateList != null) {
            return;
        }

        dateList = new HashSet<>(16);
    }

}
