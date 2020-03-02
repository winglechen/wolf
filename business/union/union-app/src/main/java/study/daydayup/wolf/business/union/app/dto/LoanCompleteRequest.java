package study.daydayup.wolf.business.union.app.dto;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * study.daydayup.wolf.business.union.app.dto
 *
 * @author Wingle
 * @since 2020/3/2 6:56 下午
 **/
@Data
public class LoanCompleteRequest implements Request {
    @NotBlank
    private String tradeNo;
    private LocalDate effectAt;
}
