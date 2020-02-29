package study.daydayup.wolf.business.pay.biz.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PaymentDO implements Serializable {
    private Long id;

    private String paymentNo;

    private Long payerId;

    private String payerName;

    private Long payeeId;

    private String payeeName;

    private BigDecimal amount;

    private Integer state;

    private Integer paymentType;

    private Integer paymentMethod;

    private String tradeNo;

    private String outTradeNo;

    private String attachment;

    private String tags;

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
        sb.append(", paymentNo=").append(paymentNo);
        sb.append(", payerId=").append(payerId);
        sb.append(", payerName=").append(payerName);
        sb.append(", payeeId=").append(payeeId);
        sb.append(", payeeName=").append(payeeName);
        sb.append(", amount=").append(amount);
        sb.append(", state=").append(state);
        sb.append(", paymentType=").append(paymentType);
        sb.append(", paymentMethod=").append(paymentMethod);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", outTradeNo=").append(outTradeNo);
        sb.append(", attachment=").append(attachment);
        sb.append(", tags=").append(tags);
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
        PaymentDO other = (PaymentDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPaymentNo() == null ? other.getPaymentNo() == null : this.getPaymentNo().equals(other.getPaymentNo()))
            && (this.getPayerId() == null ? other.getPayerId() == null : this.getPayerId().equals(other.getPayerId()))
            && (this.getPayerName() == null ? other.getPayerName() == null : this.getPayerName().equals(other.getPayerName()))
            && (this.getPayeeId() == null ? other.getPayeeId() == null : this.getPayeeId().equals(other.getPayeeId()))
            && (this.getPayeeName() == null ? other.getPayeeName() == null : this.getPayeeName().equals(other.getPayeeName()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getPaymentType() == null ? other.getPaymentType() == null : this.getPaymentType().equals(other.getPaymentType()))
            && (this.getPaymentMethod() == null ? other.getPaymentMethod() == null : this.getPaymentMethod().equals(other.getPaymentMethod()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getOutTradeNo() == null ? other.getOutTradeNo() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))
            && (this.getAttachment() == null ? other.getAttachment() == null : this.getAttachment().equals(other.getAttachment()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
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
        result = prime * result + ((getPaymentNo() == null) ? 0 : getPaymentNo().hashCode());
        result = prime * result + ((getPayerId() == null) ? 0 : getPayerId().hashCode());
        result = prime * result + ((getPayerName() == null) ? 0 : getPayerName().hashCode());
        result = prime * result + ((getPayeeId() == null) ? 0 : getPayeeId().hashCode());
        result = prime * result + ((getPayeeName() == null) ? 0 : getPayeeName().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getPaymentType() == null) ? 0 : getPaymentType().hashCode());
        result = prime * result + ((getPaymentMethod() == null) ? 0 : getPaymentMethod().hashCode());
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());
        result = prime * result + ((getAttachment() == null) ? 0 : getAttachment().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}