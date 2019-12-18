package study.daydayup.wolf.business.trade.order.biz.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class InstallmentTermDO implements Serializable {
    private Long id;

    private String tradeNo;

    private Long buyerId;

    private Long sellerId;

    private Integer installmentNo;

    private Integer installmentType;

    private Integer state;

    private LocalDateTime effectAt;

    private String relatedTradeNo;

    private Integer duration;

    private Integer durationUnit;

    private Integer durationStrategy;

    private Integer percentage;

    private Integer feePercentage;

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
        sb.append(", installmentNo=").append(installmentNo);
        sb.append(", installmentType=").append(installmentType);
        sb.append(", state=").append(state);
        sb.append(", effectAt=").append(effectAt);
        sb.append(", relatedTradeNo=").append(relatedTradeNo);
        sb.append(", duration=").append(duration);
        sb.append(", durationUnit=").append(durationUnit);
        sb.append(", durationStrategy=").append(durationStrategy);
        sb.append(", percentage=").append(percentage);
        sb.append(", feePercentage=").append(feePercentage);
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
        InstallmentTermDO other = (InstallmentTermDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getBuyerId() == null ? other.getBuyerId() == null : this.getBuyerId().equals(other.getBuyerId()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getInstallmentNo() == null ? other.getInstallmentNo() == null : this.getInstallmentNo().equals(other.getInstallmentNo()))
            && (this.getInstallmentType() == null ? other.getInstallmentType() == null : this.getInstallmentType().equals(other.getInstallmentType()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getEffectAt() == null ? other.getEffectAt() == null : this.getEffectAt().equals(other.getEffectAt()))
            && (this.getRelatedTradeNo() == null ? other.getRelatedTradeNo() == null : this.getRelatedTradeNo().equals(other.getRelatedTradeNo()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
            && (this.getDurationUnit() == null ? other.getDurationUnit() == null : this.getDurationUnit().equals(other.getDurationUnit()))
            && (this.getDurationStrategy() == null ? other.getDurationStrategy() == null : this.getDurationStrategy().equals(other.getDurationStrategy()))
            && (this.getPercentage() == null ? other.getPercentage() == null : this.getPercentage().equals(other.getPercentage()))
            && (this.getFeePercentage() == null ? other.getFeePercentage() == null : this.getFeePercentage().equals(other.getFeePercentage()))
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
        result = prime * result + ((getInstallmentNo() == null) ? 0 : getInstallmentNo().hashCode());
        result = prime * result + ((getInstallmentType() == null) ? 0 : getInstallmentType().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getEffectAt() == null) ? 0 : getEffectAt().hashCode());
        result = prime * result + ((getRelatedTradeNo() == null) ? 0 : getRelatedTradeNo().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getDurationUnit() == null) ? 0 : getDurationUnit().hashCode());
        result = prime * result + ((getDurationStrategy() == null) ? 0 : getDurationStrategy().hashCode());
        result = prime * result + ((getPercentage() == null) ? 0 : getPercentage().hashCode());
        result = prime * result + ((getFeePercentage() == null) ? 0 : getFeePercentage().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + (isDeleteFlag() ? 1231 : 1237);
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}