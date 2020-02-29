package study.daydayup.wolf.business.pay.biz.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PaymentStateLogDO implements Serializable {
    private Integer id;

    private String paymentNo;

    private Integer paymentMethod;

    private Long payerId;

    private Long payeeId;

    private Integer sourceState;

    private Integer targetState;

    private String tags;

    private Integer sourceVersion;

    private Integer targetVersion;

    private Long editor;

    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", paymentNo=").append(paymentNo);
        sb.append(", paymentMethod=").append(paymentMethod);
        sb.append(", payerId=").append(payerId);
        sb.append(", payeeId=").append(payeeId);
        sb.append(", sourceState=").append(sourceState);
        sb.append(", targetState=").append(targetState);
        sb.append(", tags=").append(tags);
        sb.append(", sourceVersion=").append(sourceVersion);
        sb.append(", targetVersion=").append(targetVersion);
        sb.append(", editor=").append(editor);
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
        PaymentStateLogDO other = (PaymentStateLogDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPaymentNo() == null ? other.getPaymentNo() == null : this.getPaymentNo().equals(other.getPaymentNo()))
            && (this.getPaymentMethod() == null ? other.getPaymentMethod() == null : this.getPaymentMethod().equals(other.getPaymentMethod()))
            && (this.getPayerId() == null ? other.getPayerId() == null : this.getPayerId().equals(other.getPayerId()))
            && (this.getPayeeId() == null ? other.getPayeeId() == null : this.getPayeeId().equals(other.getPayeeId()))
            && (this.getSourceState() == null ? other.getSourceState() == null : this.getSourceState().equals(other.getSourceState()))
            && (this.getTargetState() == null ? other.getTargetState() == null : this.getTargetState().equals(other.getTargetState()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getSourceVersion() == null ? other.getSourceVersion() == null : this.getSourceVersion().equals(other.getSourceVersion()))
            && (this.getTargetVersion() == null ? other.getTargetVersion() == null : this.getTargetVersion().equals(other.getTargetVersion()))
            && (this.getEditor() == null ? other.getEditor() == null : this.getEditor().equals(other.getEditor()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPaymentNo() == null) ? 0 : getPaymentNo().hashCode());
        result = prime * result + ((getPaymentMethod() == null) ? 0 : getPaymentMethod().hashCode());
        result = prime * result + ((getPayerId() == null) ? 0 : getPayerId().hashCode());
        result = prime * result + ((getPayeeId() == null) ? 0 : getPayeeId().hashCode());
        result = prime * result + ((getSourceState() == null) ? 0 : getSourceState().hashCode());
        result = prime * result + ((getTargetState() == null) ? 0 : getTargetState().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getSourceVersion() == null) ? 0 : getSourceVersion().hashCode());
        result = prime * result + ((getTargetVersion() == null) ? 0 : getTargetVersion().hashCode());
        result = prime * result + ((getEditor() == null) ? 0 : getEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}