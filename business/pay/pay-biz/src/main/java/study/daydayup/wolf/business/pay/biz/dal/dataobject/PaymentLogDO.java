package study.daydayup.wolf.business.pay.biz.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PaymentLogDO implements Serializable {
    private Long id;

    private String paymentNo;

    private Long payerId;

    private Long payeeId;

    private String tradeNo;

    private String outTradeNo;

    private Integer state;

    private Integer logType;

    private Integer paymentMethod;

    private String tags;

    private Boolean deleteFlag;

    private LocalDateTime createdAt;

    private String data;

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
        sb.append(", payeeId=").append(payeeId);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", outTradeNo=").append(outTradeNo);
        sb.append(", state=").append(state);
        sb.append(", logType=").append(logType);
        sb.append(", paymentMethod=").append(paymentMethod);
        sb.append(", tags=").append(tags);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", data=").append(data);
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
        PaymentLogDO other = (PaymentLogDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPaymentNo() == null ? other.getPaymentNo() == null : this.getPaymentNo().equals(other.getPaymentNo()))
            && (this.getPayerId() == null ? other.getPayerId() == null : this.getPayerId().equals(other.getPayerId()))
            && (this.getPayeeId() == null ? other.getPayeeId() == null : this.getPayeeId().equals(other.getPayeeId()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getOutTradeNo() == null ? other.getOutTradeNo() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getLogType() == null ? other.getLogType() == null : this.getLogType().equals(other.getLogType()))
            && (this.getPaymentMethod() == null ? other.getPaymentMethod() == null : this.getPaymentMethod().equals(other.getPaymentMethod()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getData() == null ? other.getData() == null : this.getData().equals(other.getData()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPaymentNo() == null) ? 0 : getPaymentNo().hashCode());
        result = prime * result + ((getPayerId() == null) ? 0 : getPayerId().hashCode());
        result = prime * result + ((getPayeeId() == null) ? 0 : getPayeeId().hashCode());
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getLogType() == null) ? 0 : getLogType().hashCode());
        result = prime * result + ((getPaymentMethod() == null) ? 0 : getPaymentMethod().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getData() == null) ? 0 : getData().hashCode());
        return result;
    }
}