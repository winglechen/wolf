package study.daydayup.wolf.business.trade.order.biz.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RepaymentTermDO implements Serializable {
    private Long id;

    private String tradeNo;

    private Long buyerId;

    private Long sellerId;

    private Long amount;

    private Integer currency;

    private Integer repayStrategy;

    private Integer prepayStrategy;

    private Long handlingFee;

    private Integer feePayStrategy;

    private LocalDateTime effectAt;

    private Integer duration;

    private Integer durationUnit;

    private Integer durationStrategy;

    private Integer interest;

    private Integer interestUnit;

    private Integer belatedPayment;

    private Integer belatedPaymentUnit;

    private Integer version;

    private boolean deleteFlag;

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
        sb.append(", amount=").append(amount);
        sb.append(", currency=").append(currency);
        sb.append(", repayStrategy=").append(repayStrategy);
        sb.append(", prepayStrategy=").append(prepayStrategy);
        sb.append(", handlingFee=").append(handlingFee);
        sb.append(", feePayStrategy=").append(feePayStrategy);
        sb.append(", effectAt=").append(effectAt);
        sb.append(", duration=").append(duration);
        sb.append(", durationUnit=").append(durationUnit);
        sb.append(", durationStrategy=").append(durationStrategy);
        sb.append(", interest=").append(interest);
        sb.append(", interestUnit=").append(interestUnit);
        sb.append(", belatedPayment=").append(belatedPayment);
        sb.append(", belatedPaymentUnit=").append(belatedPaymentUnit);
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
        RepaymentTermDO other = (RepaymentTermDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getBuyerId() == null ? other.getBuyerId() == null : this.getBuyerId().equals(other.getBuyerId()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getRepayStrategy() == null ? other.getRepayStrategy() == null : this.getRepayStrategy().equals(other.getRepayStrategy()))
            && (this.getPrepayStrategy() == null ? other.getPrepayStrategy() == null : this.getPrepayStrategy().equals(other.getPrepayStrategy()))
            && (this.getHandlingFee() == null ? other.getHandlingFee() == null : this.getHandlingFee().equals(other.getHandlingFee()))
            && (this.getFeePayStrategy() == null ? other.getFeePayStrategy() == null : this.getFeePayStrategy().equals(other.getFeePayStrategy()))
            && (this.getEffectAt() == null ? other.getEffectAt() == null : this.getEffectAt().equals(other.getEffectAt()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
            && (this.getDurationUnit() == null ? other.getDurationUnit() == null : this.getDurationUnit().equals(other.getDurationUnit()))
            && (this.getDurationStrategy() == null ? other.getDurationStrategy() == null : this.getDurationStrategy().equals(other.getDurationStrategy()))
            && (this.getInterest() == null ? other.getInterest() == null : this.getInterest().equals(other.getInterest()))
            && (this.getInterestUnit() == null ? other.getInterestUnit() == null : this.getInterestUnit().equals(other.getInterestUnit()))
            && (this.getBelatedPayment() == null ? other.getBelatedPayment() == null : this.getBelatedPayment().equals(other.getBelatedPayment()))
            && (this.getBelatedPaymentUnit() == null ? other.getBelatedPaymentUnit() == null : this.getBelatedPaymentUnit().equals(other.getBelatedPaymentUnit()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.isDeleteFlag() == other.isDeleteFlag())
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
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getRepayStrategy() == null) ? 0 : getRepayStrategy().hashCode());
        result = prime * result + ((getPrepayStrategy() == null) ? 0 : getPrepayStrategy().hashCode());
        result = prime * result + ((getHandlingFee() == null) ? 0 : getHandlingFee().hashCode());
        result = prime * result + ((getFeePayStrategy() == null) ? 0 : getFeePayStrategy().hashCode());
        result = prime * result + ((getEffectAt() == null) ? 0 : getEffectAt().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getDurationUnit() == null) ? 0 : getDurationUnit().hashCode());
        result = prime * result + ((getDurationStrategy() == null) ? 0 : getDurationStrategy().hashCode());
        result = prime * result + ((getInterest() == null) ? 0 : getInterest().hashCode());
        result = prime * result + ((getInterestUnit() == null) ? 0 : getInterestUnit().hashCode());
        result = prime * result + ((getBelatedPayment() == null) ? 0 : getBelatedPayment().hashCode());
        result = prime * result + ((getBelatedPaymentUnit() == null) ? 0 : getBelatedPaymentUnit().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + (isDeleteFlag() ? 1231 : 1237);
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}