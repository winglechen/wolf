package study.daydayup.wolf.business.trade.order.biz.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PriceChangeLogDO implements Serializable {
    private Integer id;

    private String tradeNo;

    private Long lineId;

    private Long buyerId;

    private Long sellerId;

    private Integer sourceVersion;

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
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", lineId=").append(lineId);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", sourceVersion=").append(sourceVersion);
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
        PriceChangeLogDO other = (PriceChangeLogDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getLineId() == null ? other.getLineId() == null : this.getLineId().equals(other.getLineId()))
            && (this.getBuyerId() == null ? other.getBuyerId() == null : this.getBuyerId().equals(other.getBuyerId()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getSourceVersion() == null ? other.getSourceVersion() == null : this.getSourceVersion().equals(other.getSourceVersion()))
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
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getLineId() == null) ? 0 : getLineId().hashCode());
        result = prime * result + ((getBuyerId() == null) ? 0 : getBuyerId().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getSourceVersion() == null) ? 0 : getSourceVersion().hashCode());
        result = prime * result + ((getSourceAmount() == null) ? 0 : getSourceAmount().hashCode());
        result = prime * result + ((getTargetAmount() == null) ? 0 : getTargetAmount().hashCode());
        result = prime * result + ((getEditor() == null) ? 0 : getEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}