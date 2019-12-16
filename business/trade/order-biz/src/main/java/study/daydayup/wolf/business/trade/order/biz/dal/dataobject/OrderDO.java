package study.daydayup.wolf.business.trade.order.biz.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OrderDO implements Serializable {
    private Long id;

    private String tradeNo;

    private Long buyerId;

    private Long sellerId;

    private String buyerName;

    private String sellerName;

    private String source;

    private String tags;

    private Integer tradeType;

    private Integer state;

    private String relatedTradeNo;

    private Long amount;

    private Long postage;

    private Integer currency;

    private Integer paymentMethod;

    private Integer consignMethod;

    private String outTradeNo;

    private Integer payState;

    private Integer consignState;

    private Integer completedState;

    private Integer disputeState;

    private LocalDateTime paidAt;

    private LocalDateTime consignedAt;

    private LocalDateTime disputeAt;

    private LocalDateTime completedAt;

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
        sb.append(", buyerName=").append(buyerName);
        sb.append(", sellerName=").append(sellerName);
        sb.append(", source=").append(source);
        sb.append(", tags=").append(tags);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", state=").append(state);
        sb.append(", relatedTradeNo=").append(relatedTradeNo);
        sb.append(", amount=").append(amount);
        sb.append(", postage=").append(postage);
        sb.append(", currency=").append(currency);
        sb.append(", paymentMethod=").append(paymentMethod);
        sb.append(", consignMethod=").append(consignMethod);
        sb.append(", outTradeNo=").append(outTradeNo);
        sb.append(", payState=").append(payState);
        sb.append(", consignState=").append(consignState);
        sb.append(", completedState=").append(completedState);
        sb.append(", disputeState=").append(disputeState);
        sb.append(", paidAt=").append(paidAt);
        sb.append(", consignedAt=").append(consignedAt);
        sb.append(", disputeAt=").append(disputeAt);
        sb.append(", completedAt=").append(completedAt);
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
        OrderDO other = (OrderDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getBuyerId() == null ? other.getBuyerId() == null : this.getBuyerId().equals(other.getBuyerId()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getBuyerName() == null ? other.getBuyerName() == null : this.getBuyerName().equals(other.getBuyerName()))
            && (this.getSellerName() == null ? other.getSellerName() == null : this.getSellerName().equals(other.getSellerName()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getTradeType() == null ? other.getTradeType() == null : this.getTradeType().equals(other.getTradeType()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getRelatedTradeNo() == null ? other.getRelatedTradeNo() == null : this.getRelatedTradeNo().equals(other.getRelatedTradeNo()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getPostage() == null ? other.getPostage() == null : this.getPostage().equals(other.getPostage()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getPaymentMethod() == null ? other.getPaymentMethod() == null : this.getPaymentMethod().equals(other.getPaymentMethod()))
            && (this.getConsignMethod() == null ? other.getConsignMethod() == null : this.getConsignMethod().equals(other.getConsignMethod()))
            && (this.getOutTradeNo() == null ? other.getOutTradeNo() == null : this.getOutTradeNo().equals(other.getOutTradeNo()))
            && (this.getPayState() == null ? other.getPayState() == null : this.getPayState().equals(other.getPayState()))
            && (this.getConsignState() == null ? other.getConsignState() == null : this.getConsignState().equals(other.getConsignState()))
            && (this.getCompletedState() == null ? other.getCompletedState() == null : this.getCompletedState().equals(other.getCompletedState()))
            && (this.getDisputeState() == null ? other.getDisputeState() == null : this.getDisputeState().equals(other.getDisputeState()))
            && (this.getPaidAt() == null ? other.getPaidAt() == null : this.getPaidAt().equals(other.getPaidAt()))
            && (this.getConsignedAt() == null ? other.getConsignedAt() == null : this.getConsignedAt().equals(other.getConsignedAt()))
            && (this.getDisputeAt() == null ? other.getDisputeAt() == null : this.getDisputeAt().equals(other.getDisputeAt()))
            && (this.getCompletedAt() == null ? other.getCompletedAt() == null : this.getCompletedAt().equals(other.getCompletedAt()))
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
        result = prime * result + ((getBuyerName() == null) ? 0 : getBuyerName().hashCode());
        result = prime * result + ((getSellerName() == null) ? 0 : getSellerName().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getTradeType() == null) ? 0 : getTradeType().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getRelatedTradeNo() == null) ? 0 : getRelatedTradeNo().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getPostage() == null) ? 0 : getPostage().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getPaymentMethod() == null) ? 0 : getPaymentMethod().hashCode());
        result = prime * result + ((getConsignMethod() == null) ? 0 : getConsignMethod().hashCode());
        result = prime * result + ((getOutTradeNo() == null) ? 0 : getOutTradeNo().hashCode());
        result = prime * result + ((getPayState() == null) ? 0 : getPayState().hashCode());
        result = prime * result + ((getConsignState() == null) ? 0 : getConsignState().hashCode());
        result = prime * result + ((getCompletedState() == null) ? 0 : getCompletedState().hashCode());
        result = prime * result + ((getDisputeState() == null) ? 0 : getDisputeState().hashCode());
        result = prime * result + ((getPaidAt() == null) ? 0 : getPaidAt().hashCode());
        result = prime * result + ((getConsignedAt() == null) ? 0 : getConsignedAt().hashCode());
        result = prime * result + ((getDisputeAt() == null) ? 0 : getDisputeAt().hashCode());
        result = prime * result + ((getCompletedAt() == null) ? 0 : getCompletedAt().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + (isDeleteFlag() ? 1231 : 1237);
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}