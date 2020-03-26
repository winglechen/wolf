package study.daydayup.wolf.bigdata.datav.api.dto.track;

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
 * @since 2020/3/26 6:02 下午
 **/
@Data
@Builder
public class RepayRequest implements Request {
    @NotNull @Positive
    private Long orgId;
    private Long goodsId;
    private Integer installmentNo;

    private LocalDate startDate;
    private LocalDate endDate;
}
