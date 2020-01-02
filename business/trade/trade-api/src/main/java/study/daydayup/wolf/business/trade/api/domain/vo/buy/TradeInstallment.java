package study.daydayup.wolf.business.trade.api.domain.vo.buy;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * study.daydayup.wolf.business.trade.api.domain.vo.buy
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
