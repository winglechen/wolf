package study.daydayup.wolf.bigdata.datav.api.dto.daily;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

/**
 * study.daydayup.wolf.bigdata.datav.api.dto.daily
 *
 * @author Wingle
 * @since 2020/3/26 12:00 上午
 **/
@Data
@Builder
public class DateRangeRequest implements Request {
    private Long orgId;
    private String source;

    private LocalDate date;
    private LocalDate startDate;
    private LocalDate endDate;

}
