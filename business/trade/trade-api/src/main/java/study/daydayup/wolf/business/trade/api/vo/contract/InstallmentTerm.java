package study.daydayup.wolf.business.trade.api.vo.contract;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * study.daydayup.wolf.business.trade.api.vo.contract
 *
 * @author Wingle
 * @since 2019/12/13 3:53 下午
 **/
@Data
@Builder
public class InstallmentTerm extends ContractTerm {
    private String tradeNo;
    private long buyerId;
    private long sellerId;

    private int installmentNo;

    private int duration;
    private int percentage;
    private int feePercentage;
    private int installmentType;

    private long amount;
    private long interest;
    private long handlingFee;

    private LocalDateTime effectAt;
    private LocalDateTime dueAt;
    private LocalDateTime overdueAt;

    private int version;
}
