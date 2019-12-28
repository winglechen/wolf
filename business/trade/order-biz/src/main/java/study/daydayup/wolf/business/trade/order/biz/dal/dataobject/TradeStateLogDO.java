package study.daydayup.wolf.business.trade.order.biz.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TradeStateLogDO implements Serializable {
    private Integer id;

    private String tradeNo;

    private String relatedTradeNo;

    private Integer tradeType;

    private Long buyerId;

    private Long sellerId;

    private Integer sourceState;

    private Integer targetState;

    private Long amount;

    private Integer currency;

    private Integer paymentMethod;

    private Integer consignMethod;

    private String tags;

    private String source;

    private Integer sourceVersion;

    private Integer targetVersion;

    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", relatedTradeNo=").append(relatedTradeNo);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", sourceState=").append(sourceState);
        sb.append(", targetState=").append(targetState);
        sb.append(", amount=").append(amount);
        sb.append(", currency=").append(currency);
        sb.append(", paymentMethod=").append(paymentMethod);
        sb.append(", consignMethod=").append(consignMethod);
        sb.append(", tags=").append(tags);
        sb.append(", source=").append(source);
        sb.append(", sourceVersion=").append(sourceVersion);
        sb.append(", targetVersion=").append(targetVersion);
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
        TradeStateLogDO other = (TradeStateLogDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getRelatedTradeNo() == null ? other.getRelatedTradeNo() == null : this.getRelatedTradeNo().equals(other.getRelatedTradeNo()))
            && (this.getTradeType() == null ? other.getTradeType() == null : this.getTradeType().equals(other.getTradeType()))
            && (this.getBuyerId() == null ? other.getBuyerId() == null : this.getBuyerId().equals(other.getBuyerId()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getSourceState() == null ? other.getSourceState() == null : this.getSourceState().equals(other.getSourceState()))
            && (this.getTargetState() == null ? other.getTargetState() == null : this.getTargetState().equals(other.getTargetState()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getPaymentMethod() == null ? other.getPaymentMethod() == null : this.getPaymentMethod().equals(other.getPaymentMethod()))
            && (this.getConsignMethod() == null ? other.getConsignMethod() == null : this.getConsignMethod().equals(other.getConsignMethod()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getSourceVersion() == null ? other.getSourceVersion() == null : this.getSourceVersion().equals(other.getSourceVersion()))
            && (this.getTargetVersion() == null ? other.getTargetVersion() == null : this.getTargetVersion().equals(other.getTargetVersion()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getRelatedTradeNo() == null) ? 0 : getRelatedTradeNo().hashCode());
        result = prime * result + ((getTradeType() == null) ? 0 : getTradeType().hashCode());
        result = prime * result + ((getBuyerId() == null) ? 0 : getBuyerId().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getSourceState() == null) ? 0 : getSourceState().hashCode());
        result = prime * result + ((getTargetState() == null) ? 0 : getTargetState().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getPaymentMethod() == null) ? 0 : getPaymentMethod().hashCode());
        result = prime * result + ((getConsignMethod() == null) ? 0 : getConsignMethod().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getSourceVersion() == null) ? 0 : getSourceVersion().hashCode());
        result = prime * result + ((getTargetVersion() == null) ? 0 : getTargetVersion().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}