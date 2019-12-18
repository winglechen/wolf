package study.daydayup.wolf.business.trade.api.vo.buy;

import lombok.Data;
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
public class TradeInstallment extends VO {
    private int duration;
    private int durationUnit;
    private int durationStrategy;

    private int percentage;
    private int feePercentage;
    private int installmentType;

    private LocalDateTime effectAt;
    private int installmentNo;
}
