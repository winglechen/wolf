package study.daydayup.wolf.business.goods.biz.dal.dataobject;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class GoodsLoanDO implements Serializable {
    private Long id;

    private Long goodsId;

    private Long orgId;

    private Integer repayStrategy;

    private Integer prepayStrategy;

    private Integer amountStrategy;

    private Integer handlingFeeRate;

    private Integer feePayStrategy;

    private Integer duration;

    private Integer durationUnit;

    private Integer durationStrategy;

    private Integer interest;

    private Integer interestUnit;

    private Integer belatedPayment;

    private Integer belatedPaymentUnit;

    private String installment;

    private Integer version;

    private boolean deleteFlag;

    private Long lastEditor;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", orgId=").append(orgId);
        sb.append(", repayStrategy=").append(repayStrategy);
        sb.append(", prepayStrategy=").append(prepayStrategy);
        sb.append(", amountStrategy=").append(amountStrategy);
        sb.append(", handlingFeeRate=").append(handlingFeeRate);
        sb.append(", feePayStrategy=").append(feePayStrategy);
        sb.append(", duration=").append(duration);
        sb.append(", durationUnit=").append(durationUnit);
        sb.append(", durationStrategy=").append(durationStrategy);
        sb.append(", interest=").append(interest);
        sb.append(", interestUnit=").append(interestUnit);
        sb.append(", belatedPayment=").append(belatedPayment);
        sb.append(", belatedPaymentUnit=").append(belatedPaymentUnit);
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
        GoodsLoanDO other = (GoodsLoanDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getRepayStrategy() == null ? other.getRepayStrategy() == null : this.getRepayStrategy().equals(other.getRepayStrategy()))
            && (this.getPrepayStrategy() == null ? other.getPrepayStrategy() == null : this.getPrepayStrategy().equals(other.getPrepayStrategy()))
            && (this.getAmountStrategy() == null ? other.getAmountStrategy() == null : this.getAmountStrategy().equals(other.getAmountStrategy()))
            && (this.getHandlingFeeRate() == null ? other.getHandlingFeeRate() == null : this.getHandlingFeeRate().equals(other.getHandlingFeeRate()))
            && (this.getFeePayStrategy() == null ? other.getFeePayStrategy() == null : this.getFeePayStrategy().equals(other.getFeePayStrategy()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
            && (this.getDurationUnit() == null ? other.getDurationUnit() == null : this.getDurationUnit().equals(other.getDurationUnit()))
            && (this.getDurationStrategy() == null ? other.getDurationStrategy() == null : this.getDurationStrategy().equals(other.getDurationStrategy()))
            && (this.getInterest() == null ? other.getInterest() == null : this.getInterest().equals(other.getInterest()))
            && (this.getInterestUnit() == null ? other.getInterestUnit() == null : this.getInterestUnit().equals(other.getInterestUnit()))
            && (this.getBelatedPayment() == null ? other.getBelatedPayment() == null : this.getBelatedPayment().equals(other.getBelatedPayment()))
            && (this.getBelatedPaymentUnit() == null ? other.getBelatedPaymentUnit() == null : this.getBelatedPaymentUnit().equals(other.getBelatedPaymentUnit()))
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
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getRepayStrategy() == null) ? 0 : getRepayStrategy().hashCode());
        result = prime * result + ((getPrepayStrategy() == null) ? 0 : getPrepayStrategy().hashCode());
        result = prime * result + ((getAmountStrategy() == null) ? 0 : getAmountStrategy().hashCode());
        result = prime * result + ((getHandlingFeeRate() == null) ? 0 : getHandlingFeeRate().hashCode());
        result = prime * result + ((getFeePayStrategy() == null) ? 0 : getFeePayStrategy().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getDurationUnit() == null) ? 0 : getDurationUnit().hashCode());
        result = prime * result + ((getDurationStrategy() == null) ? 0 : getDurationStrategy().hashCode());
        result = prime * result + ((getInterest() == null) ? 0 : getInterest().hashCode());
        result = prime * result + ((getInterestUnit() == null) ? 0 : getInterestUnit().hashCode());
        result = prime * result + ((getBelatedPayment() == null) ? 0 : getBelatedPayment().hashCode());
        result = prime * result + ((getBelatedPaymentUnit() == null) ? 0 : getBelatedPaymentUnit().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + (isDeleteFlag() ? 1231 : 1237);
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}