package study.daydayup.wolf.business.pay.biz.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TransactionDO implements Serializable {
    private Long id;

    private Long payeeId;

    private Long payerId;

    private String payerName;

    private String payerPhone;

    private String payerEmail;

    private String paymentNo;

    private Integer transactionType;

    private Integer paymentChannel;

    private Integer currency;

    private BigDecimal amount;

    private String settlementNo;

    private Integer settlementState;

    private Integer notifyState;

    private LocalDateTime settledAt;

    private LocalDateTime paidAt;

    private LocalDateTime outPaidAt;

    private String attachment;

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
        sb.append(", payeeId=").append(payeeId);
        sb.append(", payerId=").append(payerId);
        sb.append(", payerName=").append(payerName);
        sb.append(", payerPhone=").append(payerPhone);
        sb.append(", payerEmail=").append(payerEmail);
        sb.append(", paymentNo=").append(paymentNo);
        sb.append(", transactionType=").append(transactionType);
        sb.append(", paymentChannel=").append(paymentChannel);
        sb.append(", currency=").append(currency);
        sb.append(", amount=").append(amount);
        sb.append(", settlementNo=").append(settlementNo);
        sb.append(", settlementState=").append(settlementState);
        sb.append(", notifyState=").append(notifyState);
        sb.append(", settledAt=").append(settledAt);
        sb.append(", paidAt=").append(paidAt);
        sb.append(", outPaidAt=").append(outPaidAt);
        sb.append(", attachment=").append(attachment);
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
        TransactionDO other = (TransactionDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPayeeId() == null ? other.getPayeeId() == null : this.getPayeeId().equals(other.getPayeeId()))
            && (this.getPayerId() == null ? other.getPayerId() == null : this.getPayerId().equals(other.getPayerId()))
            && (this.getPayerName() == null ? other.getPayerName() == null : this.getPayerName().equals(other.getPayerName()))
            && (this.getPayerPhone() == null ? other.getPayerPhone() == null : this.getPayerPhone().equals(other.getPayerPhone()))
            && (this.getPayerEmail() == null ? other.getPayerEmail() == null : this.getPayerEmail().equals(other.getPayerEmail()))
            && (this.getPaymentNo() == null ? other.getPaymentNo() == null : this.getPaymentNo().equals(other.getPaymentNo()))
            && (this.getTransactionType() == null ? other.getTransactionType() == null : this.getTransactionType().equals(other.getTransactionType()))
            && (this.getPaymentChannel() == null ? other.getPaymentChannel() == null : this.getPaymentChannel().equals(other.getPaymentChannel()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getSettlementNo() == null ? other.getSettlementNo() == null : this.getSettlementNo().equals(other.getSettlementNo()))
            && (this.getSettlementState() == null ? other.getSettlementState() == null : this.getSettlementState().equals(other.getSettlementState()))
            && (this.getNotifyState() == null ? other.getNotifyState() == null : this.getNotifyState().equals(other.getNotifyState()))
            && (this.getSettledAt() == null ? other.getSettledAt() == null : this.getSettledAt().equals(other.getSettledAt()))
            && (this.getPaidAt() == null ? other.getPaidAt() == null : this.getPaidAt().equals(other.getPaidAt()))
            && (this.getOutPaidAt() == null ? other.getOutPaidAt() == null : this.getOutPaidAt().equals(other.getOutPaidAt()))
            && (this.getAttachment() == null ? other.getAttachment() == null : this.getAttachment().equals(other.getAttachment()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPayeeId() == null) ? 0 : getPayeeId().hashCode());
        result = prime * result + ((getPayerId() == null) ? 0 : getPayerId().hashCode());
        result = prime * result + ((getPayerName() == null) ? 0 : getPayerName().hashCode());
        result = prime * result + ((getPayerPhone() == null) ? 0 : getPayerPhone().hashCode());
        result = prime * result + ((getPayerEmail() == null) ? 0 : getPayerEmail().hashCode());
        result = prime * result + ((getPaymentNo() == null) ? 0 : getPaymentNo().hashCode());
        result = prime * result + ((getTransactionType() == null) ? 0 : getTransactionType().hashCode());
        result = prime * result + ((getPaymentChannel() == null) ? 0 : getPaymentChannel().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getSettlementNo() == null) ? 0 : getSettlementNo().hashCode());
        result = prime * result + ((getSettlementState() == null) ? 0 : getSettlementState().hashCode());
        result = prime * result + ((getNotifyState() == null) ? 0 : getNotifyState().hashCode());
        result = prime * result + ((getSettledAt() == null) ? 0 : getSettledAt().hashCode());
        result = prime * result + ((getPaidAt() == null) ? 0 : getPaidAt().hashCode());
        result = prime * result + ((getOutPaidAt() == null) ? 0 : getOutPaidAt().hashCode());
        result = prime * result + ((getAttachment() == null) ? 0 : getAttachment().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}