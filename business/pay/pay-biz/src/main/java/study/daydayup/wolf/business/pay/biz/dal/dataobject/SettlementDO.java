package study.daydayup.wolf.business.pay.biz.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SettlementDO implements Serializable {
    private Long id;

    private Long accountId;

    private String settlementNo;

    private Integer settlementType;

    private Integer state;

    private Integer currency;

    private Integer transactionCount;

    private BigDecimal transactionAmount;

    private Integer deductCount;

    private BigDecimal deductAmount;

    private Integer feeCount;

    private BigDecimal feeAmount;

    private Integer taxCount;

    private BigDecimal taxAmount;

    private LocalDateTime settledAt;

    private Boolean deleteFlag;

    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", accountId=").append(accountId);
        sb.append(", settlementNo=").append(settlementNo);
        sb.append(", settlementType=").append(settlementType);
        sb.append(", state=").append(state);
        sb.append(", currency=").append(currency);
        sb.append(", transactionCount=").append(transactionCount);
        sb.append(", transactionAmount=").append(transactionAmount);
        sb.append(", deductCount=").append(deductCount);
        sb.append(", deductAmount=").append(deductAmount);
        sb.append(", feeCount=").append(feeCount);
        sb.append(", feeAmount=").append(feeAmount);
        sb.append(", taxCount=").append(taxCount);
        sb.append(", taxAmount=").append(taxAmount);
        sb.append(", settledAt=").append(settledAt);
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
        SettlementDO other = (SettlementDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getSettlementNo() == null ? other.getSettlementNo() == null : this.getSettlementNo().equals(other.getSettlementNo()))
            && (this.getSettlementType() == null ? other.getSettlementType() == null : this.getSettlementType().equals(other.getSettlementType()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getTransactionCount() == null ? other.getTransactionCount() == null : this.getTransactionCount().equals(other.getTransactionCount()))
            && (this.getTransactionAmount() == null ? other.getTransactionAmount() == null : this.getTransactionAmount().equals(other.getTransactionAmount()))
            && (this.getDeductCount() == null ? other.getDeductCount() == null : this.getDeductCount().equals(other.getDeductCount()))
            && (this.getDeductAmount() == null ? other.getDeductAmount() == null : this.getDeductAmount().equals(other.getDeductAmount()))
            && (this.getFeeCount() == null ? other.getFeeCount() == null : this.getFeeCount().equals(other.getFeeCount()))
            && (this.getFeeAmount() == null ? other.getFeeAmount() == null : this.getFeeAmount().equals(other.getFeeAmount()))
            && (this.getTaxCount() == null ? other.getTaxCount() == null : this.getTaxCount().equals(other.getTaxCount()))
            && (this.getTaxAmount() == null ? other.getTaxAmount() == null : this.getTaxAmount().equals(other.getTaxAmount()))
            && (this.getSettledAt() == null ? other.getSettledAt() == null : this.getSettledAt().equals(other.getSettledAt()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getSettlementNo() == null) ? 0 : getSettlementNo().hashCode());
        result = prime * result + ((getSettlementType() == null) ? 0 : getSettlementType().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getTransactionCount() == null) ? 0 : getTransactionCount().hashCode());
        result = prime * result + ((getTransactionAmount() == null) ? 0 : getTransactionAmount().hashCode());
        result = prime * result + ((getDeductCount() == null) ? 0 : getDeductCount().hashCode());
        result = prime * result + ((getDeductAmount() == null) ? 0 : getDeductAmount().hashCode());
        result = prime * result + ((getFeeCount() == null) ? 0 : getFeeCount().hashCode());
        result = prime * result + ((getFeeAmount() == null) ? 0 : getFeeAmount().hashCode());
        result = prime * result + ((getTaxCount() == null) ? 0 : getTaxCount().hashCode());
        result = prime * result + ((getTaxAmount() == null) ? 0 : getTaxAmount().hashCode());
        result = prime * result + ((getSettledAt() == null) ? 0 : getSettledAt().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}