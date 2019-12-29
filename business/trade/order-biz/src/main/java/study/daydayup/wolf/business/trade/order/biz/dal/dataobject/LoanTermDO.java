package study.daydayup.wolf.business.trade.order.biz.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class LoanTermDO implements Serializable {
    private Long id;

    private String tradeNo;

    private Long buyerId;

    private Long sellerId;

    private Integer state;

    private Long amount;

    private Integer currency;

    private Integer installmentNum;

    private Integer repayStrategy;

    private Integer prepayStrategy;

    private Long handlingFee;

    private Integer feePayStrategy;

    private Integer period;

    private Integer periodUnit;

    private Integer periodStrategy;

    private Integer interest;

    private Integer interestUnit;

    private Integer penalty;

    private Integer penaltyUnit;

    private Integer version;

    private Boolean deleteFlag;

    private Long lastEditor;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", state=").append(state);
        sb.append(", amount=").append(amount);
        sb.append(", currency=").append(currency);
        sb.append(", installmentNum=").append(installmentNum);
        sb.append(", repayStrategy=").append(repayStrategy);
        sb.append(", prepayStrategy=").append(prepayStrategy);
        sb.append(", handlingFee=").append(handlingFee);
        sb.append(", feePayStrategy=").append(feePayStrategy);
        sb.append(", period=").append(period);
        sb.append(", periodUnit=").append(periodUnit);
        sb.append(", periodStrategy=").append(periodStrategy);
        sb.append(", interest=").append(interest);
        sb.append(", interestUnit=").append(interestUnit);
        sb.append(", penalty=").append(penalty);
        sb.append(", penaltyUnit=").append(penaltyUnit);
        sb.append(", version=").append(version);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", lastEditor=").append(lastEditor);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LoanTermDO other = (LoanTermDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getBuyerId() == null ? other.getBuyerId() == null : this.getBuyerId().equals(other.getBuyerId()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getInstallmentNum() == null ? other.getInstallmentNum() == null : this.getInstallmentNum().equals(other.getInstallmentNum()))
            && (this.getRepayStrategy() == null ? other.getRepayStrategy() == null : this.getRepayStrategy().equals(other.getRepayStrategy()))
            && (this.getPrepayStrategy() == null ? other.getPrepayStrategy() == null : this.getPrepayStrategy().equals(other.getPrepayStrategy()))
            && (this.getHandlingFee() == null ? other.getHandlingFee() == null : this.getHandlingFee().equals(other.getHandlingFee()))
            && (this.getFeePayStrategy() == null ? other.getFeePayStrategy() == null : this.getFeePayStrategy().equals(other.getFeePayStrategy()))
            && (this.getPeriod() == null ? other.getPeriod() == null : this.getPeriod().equals(other.getPeriod()))
            && (this.getPeriodUnit() == null ? other.getPeriodUnit() == null : this.getPeriodUnit().equals(other.getPeriodUnit()))
            && (this.getPeriodStrategy() == null ? other.getPeriodStrategy() == null : this.getPeriodStrategy().equals(other.getPeriodStrategy()))
            && (this.getInterest() == null ? other.getInterest() == null : this.getInterest().equals(other.getInterest()))
            && (this.getInterestUnit() == null ? other.getInterestUnit() == null : this.getInterestUnit().equals(other.getInterestUnit()))
            && (this.getPenalty() == null ? other.getPenalty() == null : this.getPenalty().equals(other.getPenalty()))
            && (this.getPenaltyUnit() == null ? other.getPenaltyUnit() == null : this.getPenaltyUnit().equals(other.getPenaltyUnit()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getLastEditor() == null ? other.getLastEditor() == null : this.getLastEditor().equals(other.getLastEditor()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getBuyerId() == null) ? 0 : getBuyerId().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getInstallmentNum() == null) ? 0 : getInstallmentNum().hashCode());
        result = prime * result + ((getRepayStrategy() == null) ? 0 : getRepayStrategy().hashCode());
        result = prime * result + ((getPrepayStrategy() == null) ? 0 : getPrepayStrategy().hashCode());
        result = prime * result + ((getHandlingFee() == null) ? 0 : getHandlingFee().hashCode());
        result = prime * result + ((getFeePayStrategy() == null) ? 0 : getFeePayStrategy().hashCode());
        result = prime * result + ((getPeriod() == null) ? 0 : getPeriod().hashCode());
        result = prime * result + ((getPeriodUnit() == null) ? 0 : getPeriodUnit().hashCode());
        result = prime * result + ((getPeriodStrategy() == null) ? 0 : getPeriodStrategy().hashCode());
        result = prime * result + ((getInterest() == null) ? 0 : getInterest().hashCode());
        result = prime * result + ((getInterestUnit() == null) ? 0 : getInterestUnit().hashCode());
        result = prime * result + ((getPenalty() == null) ? 0 : getPenalty().hashCode());
        result = prime * result + ((getPenaltyUnit() == null) ? 0 : getPenaltyUnit().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}