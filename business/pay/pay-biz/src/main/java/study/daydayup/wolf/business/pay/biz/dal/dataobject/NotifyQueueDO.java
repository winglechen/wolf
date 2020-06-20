package study.daydayup.wolf.business.pay.biz.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class NotifyQueueDO implements Serializable {
    private Long id;

    private Long payerId;

    private Long payeeId;

    private String paymentNo;

    private String outTradeNo;

    private Integer state;

    private String notifyUrl;

    private String notifyData;

    private String notifyResponse;

    private LocalDateTime notifyAt;

    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", payerId=").append(payerId);
        sb.append(", payeeId=").append(payeeId);
        sb.append(", paymentNo=").append(paymentNo);
        sb.append(", outTradeNo=").append(outTradeNo);
        sb.append(", state=").append(state);
        sb.append(", notifyUrl=").append(notifyUrl);
        sb.append(", notifyData=").append(notifyData);
        sb.append(", notifyResponse=").append(notifyResponse);
        sb.append(", notifyAt=").append(notifyAt);
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
        NotifyQueueDO other = (NotifyQueueDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPayerId() == null ? other.getPayerId() == null : this.getPayerId().equals(other.getPayerId()))
            && (this.getPayeeId() == null ? other.getPayeeId() == null : this.getPayeeId().equals(other.getPayeeId()))
            && (this.getPaymentNo() == null ? other.getPaymentNo() == null : this.getPaymentNo().equals(other.getPaymentNo()))
            && (this.getOutTradeNo() == null ? other.getOutTradeNo() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getNotifyUrl() == null ? other.getNotifyUrl() == null : this.getNotifyUrl().equals(other.getNotifyUrl()))
            && (this.getNotifyData() == null ? other.getNotifyData() == null : this.getNotifyData().equals(other.getNotifyData()))
            && (this.getNotifyResponse() == null ? other.getNotifyResponse() == null : this.getNotifyResponse().equals(other.getNotifyResponse()))
            && (this.getNotifyAt() == null ? other.getNotifyAt() == null : this.getNotifyAt().equals(other.getNotifyAt()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPayerId() == null) ? 0 : getPayerId().hashCode());
        result = prime * result + ((getPayeeId() == null) ? 0 : getPayeeId().hashCode());
        result = prime * result + ((getPaymentNo() == null) ? 0 : getPaymentNo().hashCode());
        result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getNotifyUrl() == null) ? 0 : getNotifyUrl().hashCode());
        result = prime * result + ((getNotifyData() == null) ? 0 : getNotifyData().hashCode());
        result = prime * result + ((getNotifyResponse() == null) ? 0 : getNotifyResponse().hashCode());
        result = prime * result + ((getNotifyAt() == null) ? 0 : getNotifyAt().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}