package study.daydayup.wolf.business.org.biz.task.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TaskTradeDO implements Serializable {
    private Long id;

    private Long orgId;

    private Long taskId;

    private String tradeNo;

    private Byte installmentNo;

    private Byte tradeType;

    private BigDecimal amount;

    private Integer currency;

    private BigDecimal paidAmount;

    private BigDecimal payingAmount;

    private BigDecimal interest;

    private BigDecimal interestRate;

    private Integer interestUnit;

    private LocalDate dueAt;

    private Integer periodStrategy;

    private Integer deleteFlag;

    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orgId=").append(orgId);
        sb.append(", taskId=").append(taskId);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", installmentNo=").append(installmentNo);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", amount=").append(amount);
        sb.append(", currency=").append(currency);
        sb.append(", paidAmount=").append(paidAmount);
        sb.append(", payingAmount=").append(payingAmount);
        sb.append(", interest=").append(interest);
        sb.append(", interestRate=").append(interestRate);
        sb.append(", interestUnit=").append(interestUnit);
        sb.append(", dueAt=").append(dueAt);
        sb.append(", periodStrategy=").append(periodStrategy);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", createdAt=").append(createdAt);
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
        TaskTradeDO other = (TaskTradeDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getInstallmentNo() == null ? other.getInstallmentNo() == null : this.getInstallmentNo().equals(other.getInstallmentNo()))
            && (this.getTradeType() == null ? other.getTradeType() == null : this.getTradeType().equals(other.getTradeType()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getPaidAmount() == null ? other.getPaidAmount() == null : this.getPaidAmount().equals(other.getPaidAmount()))
            && (this.getPayingAmount() == null ? other.getPayingAmount() == null : this.getPayingAmount().equals(other.getPayingAmount()))
            && (this.getInterest() == null ? other.getInterest() == null : this.getInterest().equals(other.getInterest()))
            && (this.getInterestRate() == null ? other.getInterestRate() == null : this.getInterestRate().equals(other.getInterestRate()))
            && (this.getInterestUnit() == null ? other.getInterestUnit() == null : this.getInterestUnit().equals(other.getInterestUnit()))
            && (this.getDueAt() == null ? other.getDueAt() == null : this.getDueAt().equals(other.getDueAt()))
            && (this.getPeriodStrategy() == null ? other.getPeriodStrategy() == null : this.getPeriodStrategy().equals(other.getPeriodStrategy()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getInstallmentNo() == null) ? 0 : getInstallmentNo().hashCode());
        result = prime * result + ((getTradeType() == null) ? 0 : getTradeType().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getPaidAmount() == null) ? 0 : getPaidAmount().hashCode());
        result = prime * result + ((getPayingAmount() == null) ? 0 : getPayingAmount().hashCode());
        result = prime * result + ((getInterest() == null) ? 0 : getInterest().hashCode());
        result = prime * result + ((getInterestRate() == null) ? 0 : getInterestRate().hashCode());
        result = prime * result + ((getInterestUnit() == null) ? 0 : getInterestUnit().hashCode());
        result = prime * result + ((getDueAt() == null) ? 0 : getDueAt().hashCode());
        result = prime * result + ((getPeriodStrategy() == null) ? 0 : getPeriodStrategy().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}