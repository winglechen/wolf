package study.daydayup.wolf.business.trade.api.vo.buy;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.domain.VO;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.trade.api.vo.buy
 *
 * @author Wingle
 * @since 2019/12/18 10:57 上午
 **/
@Data
@NoArgsConstructor
public class TradeInstallment {
    private Integer installmentNo;

    private Integer period;

    private Integer percentage;
    private Integer feePercentage;
    private Integer installmentType;
}
