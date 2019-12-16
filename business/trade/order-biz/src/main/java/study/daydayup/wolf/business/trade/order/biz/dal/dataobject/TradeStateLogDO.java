package study.daydayup.wolf.business.trade.order.biz.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TradeStateLogDO implements Serializable {
    private Integer id;

    private String orderNo;

    private String contractNo;

    private Long buyerId;

    private Long sellerId;

    private Integer orderVersion;

    private Long sourceAmount;

    private Long targetAmount;

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
        sb.append(", orderNo=").append(orderNo);
        sb.append(", contractNo=").append(contractNo);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", orderVersion=").append(orderVersion);
        sb.append(", sourceAmount=").append(sourceAmount);
        sb.append(", targetAmount=").append(targetAmount);
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
        TradeStateLogDO other = (TradeStateLogDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getContractNo() == null ? other.getContractNo() == null : this.getContractNo().equals(other.getContractNo()))
            && (this.getBuyerId() == null ? other.getBuyerId() == null : this.getBuyerId().equals(other.getBuyerId()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getOrderVersion() == null ? other.getOrderVersion() == null : this.getOrderVersion().equals(other.getOrderVersion()))
            && (this.getSourceAmount() == null ? other.getSourceAmount() == null : this.getSourceAmount().equals(other.getSourceAmount()))
            && (this.getTargetAmount() == null ? other.getTargetAmount() == null : this.getTargetAmount().equals(other.getTargetAmount()))
            && (this.getEditor() == null ? other.getEditor() == null : this.getEditor().equals(other.getEditor()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getContractNo() == null) ? 0 : getContractNo().hashCode());
        result = prime * result + ((getBuyerId() == null) ? 0 : getBuyerId().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getOrderVersion() == null) ? 0 : getOrderVersion().hashCode());
        result = prime * result + ((getSourceAmount() == null) ? 0 : getSourceAmount().hashCode());
        result = prime * result + ((getTargetAmount() == null) ? 0 : getTargetAmount().hashCode());
        result = prime * result + ((getEditor() == null) ? 0 : getEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}