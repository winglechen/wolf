package study.daydayup.wolf.bigdata.datav.biz.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DailyRepayDO implements Serializable {
    private Long id;

    private Long orgId;

    private LocalDate date;

    private Integer installmentNo;

    private Integer dueCount;

    private Integer firstDueCount;

    private BigDecimal dueAmount;

    private BigDecimal firstDueAmount;

    private Integer overdueCount;

    private BigDecimal overdueAmount;

    private Integer firstOverdueCount;

    private BigDecimal firstOverdueAmount;

    private Integer repayCount;

    private Integer firstRepayCount;

    private BigDecimal repayAmount;

    private BigDecimal firstRepayAmount;

    private Integer lossCount;

    private Integer firstLossCount;

    private BigDecimal lossAmount;

    private BigDecimal firstLossAmount;

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
        sb.append(", orgId=").append(orgId);
        sb.append(", date=").append(date);
        sb.append(", installmentNo=").append(installmentNo);
        sb.append(", dueCount=").append(dueCount);
        sb.append(", firstDueCount=").append(firstDueCount);
        sb.append(", dueAmount=").append(dueAmount);
        sb.append(", firstDueAmount=").append(firstDueAmount);
        sb.append(", overdueCount=").append(overdueCount);
        sb.append(", overdueAmount=").append(overdueAmount);
        sb.append(", firstOverdueCount=").append(firstOverdueCount);
        sb.append(", firstOverdueAmount=").append(firstOverdueAmount);
        sb.append(", repayCount=").append(repayCount);
        sb.append(", firstRepayCount=").append(firstRepayCount);
        sb.append(", repayAmount=").append(repayAmount);
        sb.append(", firstRepayAmount=").append(firstRepayAmount);
        sb.append(", lossCount=").append(lossCount);
        sb.append(", firstLossCount=").append(firstLossCount);
        sb.append(", lossAmount=").append(lossAmount);
        sb.append(", firstLossAmount=").append(firstLossAmount);
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
        DailyRepayDO other = (DailyRepayDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getInstallmentNo() == null ? other.getInstallmentNo() == null : this.getInstallmentNo().equals(other.getInstallmentNo()))
            && (this.getDueCount() == null ? other.getDueCount() == null : this.getDueCount().equals(other.getDueCount()))
            && (this.getFirstDueCount() == null ? other.getFirstDueCount() == null : this.getFirstDueCount().equals(other.getFirstDueCount()))
            && (this.getDueAmount() == null ? other.getDueAmount() == null : this.getDueAmount().equals(other.getDueAmount()))
            && (this.getFirstDueAmount() == null ? other.getFirstDueAmount() == null : this.getFirstDueAmount().equals(other.getFirstDueAmount()))
            && (this.getOverdueCount() == null ? other.getOverdueCount() == null : this.getOverdueCount().equals(other.getOverdueCount()))
            && (this.getOverdueAmount() == null ? other.getOverdueAmount() == null : this.getOverdueAmount().equals(other.getOverdueAmount()))
            && (this.getFirstOverdueCount() == null ? other.getFirstOverdueCount() == null : this.getFirstOverdueCount().equals(other.getFirstOverdueCount()))
            && (this.getFirstOverdueAmount() == null ? other.getFirstOverdueAmount() == null : this.getFirstOverdueAmount().equals(other.getFirstOverdueAmount()))
            && (this.getRepayCount() == null ? other.getRepayCount() == null : this.getRepayCount().equals(other.getRepayCount()))
            && (this.getFirstRepayCount() == null ? other.getFirstRepayCount() == null : this.getFirstRepayCount().equals(other.getFirstRepayCount()))
            && (this.getRepayAmount() == null ? other.getRepayAmount() == null : this.getRepayAmount().equals(other.getRepayAmount()))
            && (this.getFirstRepayAmount() == null ? other.getFirstRepayAmount() == null : this.getFirstRepayAmount().equals(other.getFirstRepayAmount()))
            && (this.getLossCount() == null ? other.getLossCount() == null : this.getLossCount().equals(other.getLossCount()))
            && (this.getFirstLossCount() == null ? other.getFirstLossCount() == null : this.getFirstLossCount().equals(other.getFirstLossCount()))
            && (this.getLossAmount() == null ? other.getLossAmount() == null : this.getLossAmount().equals(other.getLossAmount()))
            && (this.getFirstLossAmount() == null ? other.getFirstLossAmount() == null : this.getFirstLossAmount().equals(other.getFirstLossAmount()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getInstallmentNo() == null) ? 0 : getInstallmentNo().hashCode());
        result = prime * result + ((getDueCount() == null) ? 0 : getDueCount().hashCode());
        result = prime * result + ((getFirstDueCount() == null) ? 0 : getFirstDueCount().hashCode());
        result = prime * result + ((getDueAmount() == null) ? 0 : getDueAmount().hashCode());
        result = prime * result + ((getFirstDueAmount() == null) ? 0 : getFirstDueAmount().hashCode());
        result = prime * result + ((getOverdueCount() == null) ? 0 : getOverdueCount().hashCode());
        result = prime * result + ((getOverdueAmount() == null) ? 0 : getOverdueAmount().hashCode());
        result = prime * result + ((getFirstOverdueCount() == null) ? 0 : getFirstOverdueCount().hashCode());
        result = prime * result + ((getFirstOverdueAmount() == null) ? 0 : getFirstOverdueAmount().hashCode());
        result = prime * result + ((getRepayCount() == null) ? 0 : getRepayCount().hashCode());
        result = prime * result + ((getFirstRepayCount() == null) ? 0 : getFirstRepayCount().hashCode());
        result = prime * result + ((getRepayAmount() == null) ? 0 : getRepayAmount().hashCode());
        result = prime * result + ((getFirstRepayAmount() == null) ? 0 : getFirstRepayAmount().hashCode());
        result = prime * result + ((getLossCount() == null) ? 0 : getLossCount().hashCode());
        result = prime * result + ((getFirstLossCount() == null) ? 0 : getFirstLossCount().hashCode());
        result = prime * result + ((getLossAmount() == null) ? 0 : getLossAmount().hashCode());
        result = prime * result + ((getFirstLossAmount() == null) ? 0 : getFirstLossAmount().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}