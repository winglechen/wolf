package study.daydayup.wolf.bigdata.datav.biz.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DailyLoanDO implements Serializable {
    private Long id;

    private Long orgId;

    private LocalDate date;

    private Integer requestCount;

    private Integer requestApproved;

    private Integer requestRefused;

    private Integer loanCount;

    private Integer loanAmount;

    private Integer firstLoanCount;

    private Integer firstLoanAmount;

    private Integer dueCount;

    private Integer dueAmount;

    private Integer overdueCount;

    private Integer overdueAmount;

    private Integer firstOverdueCount;

    private Integer firstOverdueAmount;

    private Integer repayCount;

    private Integer repayAmount;

    private Integer lossCount;

    private Integer lossAmount;

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
        sb.append(", requestCount=").append(requestCount);
        sb.append(", requestApproved=").append(requestApproved);
        sb.append(", requestRefused=").append(requestRefused);
        sb.append(", loanCount=").append(loanCount);
        sb.append(", loanAmount=").append(loanAmount);
        sb.append(", firstLoanCount=").append(firstLoanCount);
        sb.append(", firstLoanAmount=").append(firstLoanAmount);
        sb.append(", dueCount=").append(dueCount);
        sb.append(", dueAmount=").append(dueAmount);
        sb.append(", overdueCount=").append(overdueCount);
        sb.append(", overdueAmount=").append(overdueAmount);
        sb.append(", firstOverdueCount=").append(firstOverdueCount);
        sb.append(", firstOverdueAmount=").append(firstOverdueAmount);
        sb.append(", repayCount=").append(repayCount);
        sb.append(", repayAmount=").append(repayAmount);
        sb.append(", lossCount=").append(lossCount);
        sb.append(", lossAmount=").append(lossAmount);
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
        DailyLoanDO other = (DailyLoanDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getRequestCount() == null ? other.getRequestCount() == null : this.getRequestCount().equals(other.getRequestCount()))
            && (this.getRequestApproved() == null ? other.getRequestApproved() == null : this.getRequestApproved().equals(other.getRequestApproved()))
            && (this.getRequestRefused() == null ? other.getRequestRefused() == null : this.getRequestRefused().equals(other.getRequestRefused()))
            && (this.getLoanCount() == null ? other.getLoanCount() == null : this.getLoanCount().equals(other.getLoanCount()))
            && (this.getLoanAmount() == null ? other.getLoanAmount() == null : this.getLoanAmount().equals(other.getLoanAmount()))
            && (this.getFirstLoanCount() == null ? other.getFirstLoanCount() == null : this.getFirstLoanCount().equals(other.getFirstLoanCount()))
            && (this.getFirstLoanAmount() == null ? other.getFirstLoanAmount() == null : this.getFirstLoanAmount().equals(other.getFirstLoanAmount()))
            && (this.getDueCount() == null ? other.getDueCount() == null : this.getDueCount().equals(other.getDueCount()))
            && (this.getDueAmount() == null ? other.getDueAmount() == null : this.getDueAmount().equals(other.getDueAmount()))
            && (this.getOverdueCount() == null ? other.getOverdueCount() == null : this.getOverdueCount().equals(other.getOverdueCount()))
            && (this.getOverdueAmount() == null ? other.getOverdueAmount() == null : this.getOverdueAmount().equals(other.getOverdueAmount()))
            && (this.getFirstOverdueCount() == null ? other.getFirstOverdueCount() == null : this.getFirstOverdueCount().equals(other.getFirstOverdueCount()))
            && (this.getFirstOverdueAmount() == null ? other.getFirstOverdueAmount() == null : this.getFirstOverdueAmount().equals(other.getFirstOverdueAmount()))
            && (this.getRepayCount() == null ? other.getRepayCount() == null : this.getRepayCount().equals(other.getRepayCount()))
            && (this.getRepayAmount() == null ? other.getRepayAmount() == null : this.getRepayAmount().equals(other.getRepayAmount()))
            && (this.getLossCount() == null ? other.getLossCount() == null : this.getLossCount().equals(other.getLossCount()))
            && (this.getLossAmount() == null ? other.getLossAmount() == null : this.getLossAmount().equals(other.getLossAmount()))
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
        result = prime * result + ((getRequestCount() == null) ? 0 : getRequestCount().hashCode());
        result = prime * result + ((getRequestApproved() == null) ? 0 : getRequestApproved().hashCode());
        result = prime * result + ((getRequestRefused() == null) ? 0 : getRequestRefused().hashCode());
        result = prime * result + ((getLoanCount() == null) ? 0 : getLoanCount().hashCode());
        result = prime * result + ((getLoanAmount() == null) ? 0 : getLoanAmount().hashCode());
        result = prime * result + ((getFirstLoanCount() == null) ? 0 : getFirstLoanCount().hashCode());
        result = prime * result + ((getFirstLoanAmount() == null) ? 0 : getFirstLoanAmount().hashCode());
        result = prime * result + ((getDueCount() == null) ? 0 : getDueCount().hashCode());
        result = prime * result + ((getDueAmount() == null) ? 0 : getDueAmount().hashCode());
        result = prime * result + ((getOverdueCount() == null) ? 0 : getOverdueCount().hashCode());
        result = prime * result + ((getOverdueAmount() == null) ? 0 : getOverdueAmount().hashCode());
        result = prime * result + ((getFirstOverdueCount() == null) ? 0 : getFirstOverdueCount().hashCode());
        result = prime * result + ((getFirstOverdueAmount() == null) ? 0 : getFirstOverdueAmount().hashCode());
        result = prime * result + ((getRepayCount() == null) ? 0 : getRepayCount().hashCode());
        result = prime * result + ((getRepayAmount() == null) ? 0 : getRepayAmount().hashCode());
        result = prime * result + ((getLossCount() == null) ? 0 : getLossCount().hashCode());
        result = prime * result + ((getLossAmount() == null) ? 0 : getLossAmount().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}