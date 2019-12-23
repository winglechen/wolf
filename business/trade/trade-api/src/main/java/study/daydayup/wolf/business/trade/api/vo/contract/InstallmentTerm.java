package study.daydayup.wolf.business.trade.api.vo.contract;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


/**
 * study.daydayup.wolf.business.trade.api.vo.contract
 *
 * @author Wingle
 * @since 2019/12/13 3:53 下午
 **/
@Data
@Builder
public class InstallmentTerm {
    private String tradeNo;
    private long buyerId;
    private long sellerId;

    private int installmentNo;

    private int period;
    private int percentage;
    private int feePercentage;
    private int installmentType;

    private long amount;
    private long interest;
    private long handlingFee;

    private LocalDate effectAt;
    private LocalDate dueAt;

    private int version;
}
