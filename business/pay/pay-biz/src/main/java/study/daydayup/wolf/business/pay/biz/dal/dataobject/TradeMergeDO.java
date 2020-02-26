package study.daydayup.wolf.business.pay.biz.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TradeMergeDO implements Serializable {
    private Long id;

    private String paymentNo;

    private String tradeNo;

    private Long payerId;

    private Long payeeId;

    private BigDecimal amount;

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
        sb.append(", paymentNo=").append(paymentNo);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", payerId=").append(payerId);
        sb.append(", payeeId=").append(payeeId);
        sb.append(", amount=").append(amount);
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
        TradeMergeDO other = (TradeMergeDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPaymentNo() == null ? other.getPaymentNo() == null : this.getPaymentNo().equals(other.getPaymentNo()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getPayerId() == null ? other.getPayerId() == null : this.getPayerId().equals(other.getPayerId()))
            && (this.getPayeeId() == null ? other.getPayeeId() == null : this.getPayeeId().equals(other.getPayeeId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPaymentNo() == null) ? 0 : getPaymentNo().hashCode());
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getPayerId() == null) ? 0 : getPayerId().hashCode());
        result = prime * result + ((getPayeeId() == null) ? 0 : getPayeeId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}