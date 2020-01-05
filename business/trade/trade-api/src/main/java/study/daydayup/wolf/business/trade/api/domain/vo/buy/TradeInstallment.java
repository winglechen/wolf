package study.daydayup.wolf.business.trade.api.domain.vo.buy;

import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.domain.VO;

/**
 * study.daydayup.wolf.business.trade.api.domain.vo.buy
 *
 * @author Wingle
 * @since 2019/12/18 10:57 上午
 **/
@Data
@NoArgsConstructor
public class TradeInstallment implements VO {
    private Integer installmentNo;

    private Integer period;

    private Integer percentage;
    private Integer feePercentage;
    private Integer installmentType;
}
