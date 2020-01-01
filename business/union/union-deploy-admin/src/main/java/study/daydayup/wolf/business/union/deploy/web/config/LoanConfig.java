package study.daydayup.wolf.business.union.deploy.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.lang.enums.PeriodStrategyEnum;
import study.daydayup.wolf.common.lang.enums.finance.FeeStrategyEnum;
import study.daydayup.wolf.common.lang.enums.finance.PrepayStrategyEnum;
import study.daydayup.wolf.common.lang.enums.finance.RepayStrategyEnum;
import study.daydayup.wolf.common.lang.enums.unit.PeriodEnum;
import study.daydayup.wolf.common.lang.enums.unit.InterestEnum;

/**
 * study.daydayup.wolf.business.union.deploy.web.config
 *
 * @author Wingle
 * @since 2019/12/11 4:16 下午
 **/
@Data
@Component
@ConfigurationProperties(prefix = "wolf.loan")
public class LoanConfig {
    private int interestUnit        = InterestEnum.RATE_PER_DAY.getCode();
    private int penaltyUnit  = InterestEnum.RATE_PER_DAY.getCode();
    private int periodUnit        = PeriodEnum.DAYS.getCode();
    private int periodStrategy    = PeriodStrategyEnum.CLOSE_CLOSE.getCode();
    private int repayStrategy       = RepayStrategyEnum.CONTRACT.getCode();
    private int prepayStrategy      = PrepayStrategyEnum.ALLOW.getCode();
    private int amountStrategy      = 0;
    private int feePayStrategy      = FeeStrategyEnum.PRE.getCode();

}
