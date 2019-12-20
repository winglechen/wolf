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

    private Integer period;

    private Integer periodUnit;

    private Integer periodStrategy;

    private Integer interest;

    private Integer interestUnit;

    private Integer penalty;

    private Integer penaltyUnit;

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
        sb.append(", period=").append(period);
        sb.append(", periodUnit=").append(periodUnit);
        sb.append(", periodStrategy=").append(periodStrategy);
        sb.append(", interest=").append(interest);
        sb.append(", interestUnit=").append(interestUnit);
        sb.append(", penalty=").append(penalty);
        sb.append(", penaltyUnit=").append(penaltyUnit);
        sb.append(", installment=").append(installment);
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
            && (this.getPeriod() == null ? other.getPeriod() == null : this.getPeriod().equals(other.getPeriod()))
            && (this.getPeriodUnit() == null ? other.getPeriodUnit() == null : this.getPeriodUnit().equals(other.getPeriodUnit()))
            && (this.getPeriodStrategy() == null ? other.getPeriodStrategy() == null : this.getPeriodStrategy().equals(other.getPeriodStrategy()))
            && (this.getInterest() == null ? other.getInterest() == null : this.getInterest().equals(other.getInterest()))
            && (this.getInterestUnit() == null ? other.getInterestUnit() == null : this.getInterestUnit().equals(other.getInterestUnit()))
            && (this.getPenalty() == null ? other.getPenalty() == null : this.getPenalty().equals(other.getPenalty()))
            && (this.getPenaltyUnit() == null ? other.getPenaltyUnit() == null : this.getPenaltyUnit().equals(other.getPenaltyUnit()))
            && (this.getInstallment() == null ? other.getInstallment() == null : this.getInstallment().equals(other.getInstallment()))
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
        result = prime * result + ((getPeriod() == null) ? 0 : getPeriod().hashCode());
        result = prime * result + ((getPeriodUnit() == null) ? 0 : getPeriodUnit().hashCode());
        result = prime * result + ((getPeriodStrategy() == null) ? 0 : getPeriodStrategy().hashCode());
        result = prime * result + ((getInterest() == null) ? 0 : getInterest().hashCode());
        result = prime * result + ((getInterestUnit() == null) ? 0 : getInterestUnit().hashCode());
        result = prime * result + ((getPenalty() == null) ? 0 : getPenalty().hashCode());
        result = prime * result + ((getPenaltyUnit() == null) ? 0 : getPenaltyUnit().hashCode());
        result = prime * result + ((getInstallment() == null) ? 0 : getInstallment().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + (isDeleteFlag() ? 1231 : 1237);
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}