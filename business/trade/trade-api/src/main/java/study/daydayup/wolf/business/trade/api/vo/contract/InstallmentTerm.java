package study.daydayup.wolf.business.trade.api.vo.contract;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.state.TradeState;

import java.time.LocalDate;


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
    private Long  buyerId;
    private Long  sellerId;

    private Integer installmentNo;
    private TradeState state;
    private TradeEvent stateEvent;

    private Integer period;
    private Integer percentage;
    private Integer feePercentage;
    private Integer installmentType;

    private Long  amount;
    private Long  interest;
    private Long  handlingFee;

    private LocalDate effectAt;
    private LocalDate dueAt;

    private Integer version;
}
