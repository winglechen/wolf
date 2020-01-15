package study.daydayup.wolf.business.trade.order.biz.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RepaymentTermDO implements Serializable {
    private Long id;

    private String tradeNo;

    private Long buyerId;

    private Long sellerId;

    private Integer repayStrategy;

    private Integer prepayStrategy;

    private Integer state;

    private BigDecimal loanAmount;

    private Integer currency;

    private BigDecimal paidAmount;

    private BigDecimal lossAmount;

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
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", repayStrategy=").append(repayStrategy);
        sb.append(", prepayStrategy=").append(prepayStrategy);
        sb.append(", state=").append(state);
        sb.append(", loanAmount=").append(loanAmount);
        sb.append(", currency=").append(currency);
        sb.append(", paidAmount=").append(paidAmount);
        sb.append(", lossAmount=").append(lossAmount);
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
        RepaymentTermDO other = (RepaymentTermDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getBuyerId() == null ? other.getBuyerId() == null : this.getBuyerId().equals(other.getBuyerId()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getRepayStrategy() == null ? other.getRepayStrategy() == null : this.getRepayStrategy().equals(other.getRepayStrategy()))
            && (this.getPrepayStrategy() == null ? other.getPrepayStrategy() == null : this.getPrepayStrategy().equals(other.getPrepayStrategy()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getLoanAmount() == null ? other.getLoanAmount() == null : this.getLoanAmount().equals(other.getLoanAmount()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getPaidAmount() == null ? other.getPaidAmount() == null : this.getPaidAmount().equals(other.getPaidAmount()))
            && (this.getLossAmount() == null ? other.getLossAmount() == null : this.getLossAmount().equals(other.getLossAmount()))
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
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getBuyerId() == null) ? 0 : getBuyerId().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getRepayStrategy() == null) ? 0 : getRepayStrategy().hashCode());
        result = prime * result + ((getPrepayStrategy() == null) ? 0 : getPrepayStrategy().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getLoanAmount() == null) ? 0 : getLoanAmount().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getPaidAmount() == null) ? 0 : getPaidAmount().hashCode());
        result = prime * result + ((getLossAmount() == null) ? 0 : getLossAmount().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}