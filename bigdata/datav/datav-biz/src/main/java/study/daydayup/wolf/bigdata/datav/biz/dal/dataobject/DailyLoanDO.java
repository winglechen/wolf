package study.daydayup.wolf.bigdata.datav.biz.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DailyLoanDO implements Serializable {
    private Long id;

    private Long orgId;

    private LocalDate date;

    private Integer requestCount;

    private Integer firstRequestCount;

    private Integer requestApproved;

    private Integer firstRequestApproved;

    private Integer requestRefused;

    private Integer firstRequestRefused;

    private BigDecimal approvedRate;

    private BigDecimal firstApprovedRate;

    private Integer loanCount;

    private Integer firstLoanCount;

    private BigDecimal loanAmount;

    private BigDecimal firstLoanAmount;

    private BigDecimal loanRate;

    private BigDecimal firstLoanRate;

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
        sb.append(", firstRequestCount=").append(firstRequestCount);
        sb.append(", requestApproved=").append(requestApproved);
        sb.append(", firstRequestApproved=").append(firstRequestApproved);
        sb.append(", requestRefused=").append(requestRefused);
        sb.append(", firstRequestRefused=").append(firstRequestRefused);
        sb.append(", approvedRate=").append(approvedRate);
        sb.append(", firstApprovedRate=").append(firstApprovedRate);
        sb.append(", loanCount=").append(loanCount);
        sb.append(", firstLoanCount=").append(firstLoanCount);
        sb.append(", loanAmount=").append(loanAmount);
        sb.append(", firstLoanAmount=").append(firstLoanAmount);
        sb.append(", loanRate=").append(loanRate);
        sb.append(", firstLoanRate=").append(firstLoanRate);
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
            && (this.getFirstRequestCount() == null ? other.getFirstRequestCount() == null : this.getFirstRequestCount().equals(other.getFirstRequestCount()))
            && (this.getRequestApproved() == null ? other.getRequestApproved() == null : this.getRequestApproved().equals(other.getRequestApproved()))
            && (this.getFirstRequestApproved() == null ? other.getFirstRequestApproved() == null : this.getFirstRequestApproved().equals(other.getFirstRequestApproved()))
            && (this.getRequestRefused() == null ? other.getRequestRefused() == null : this.getRequestRefused().equals(other.getRequestRefused()))
            && (this.getFirstRequestRefused() == null ? other.getFirstRequestRefused() == null : this.getFirstRequestRefused().equals(other.getFirstRequestRefused()))
            && (this.getApprovedRate() == null ? other.getApprovedRate() == null : this.getApprovedRate().equals(other.getApprovedRate()))
            && (this.getFirstApprovedRate() == null ? other.getFirstApprovedRate() == null : this.getFirstApprovedRate().equals(other.getFirstApprovedRate()))
            && (this.getLoanCount() == null ? other.getLoanCount() == null : this.getLoanCount().equals(other.getLoanCount()))
            && (this.getFirstLoanCount() == null ? other.getFirstLoanCount() == null : this.getFirstLoanCount().equals(other.getFirstLoanCount()))
            && (this.getLoanAmount() == null ? other.getLoanAmount() == null : this.getLoanAmount().equals(other.getLoanAmount()))
            && (this.getFirstLoanAmount() == null ? other.getFirstLoanAmount() == null : this.getFirstLoanAmount().equals(other.getFirstLoanAmount()))
            && (this.getLoanRate() == null ? other.getLoanRate() == null : this.getLoanRate().equals(other.getLoanRate()))
            && (this.getFirstLoanRate() == null ? other.getFirstLoanRate() == null : this.getFirstLoanRate().equals(other.getFirstLoanRate()))
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
        result = prime * result + ((getFirstRequestCount() == null) ? 0 : getFirstRequestCount().hashCode());
        result = prime * result + ((getRequestApproved() == null) ? 0 : getRequestApproved().hashCode());
        result = prime * result + ((getFirstRequestApproved() == null) ? 0 : getFirstRequestApproved().hashCode());
        result = prime * result + ((getRequestRefused() == null) ? 0 : getRequestRefused().hashCode());
        result = prime * result + ((getFirstRequestRefused() == null) ? 0 : getFirstRequestRefused().hashCode());
        result = prime * result + ((getApprovedRate() == null) ? 0 : getApprovedRate().hashCode());
        result = prime * result + ((getFirstApprovedRate() == null) ? 0 : getFirstApprovedRate().hashCode());
        result = prime * result + ((getLoanCount() == null) ? 0 : getLoanCount().hashCode());
        result = prime * result + ((getFirstLoanCount() == null) ? 0 : getFirstLoanCount().hashCode());
        result = prime * result + ((getLoanAmount() == null) ? 0 : getLoanAmount().hashCode());
        result = prime * result + ((getFirstLoanAmount() == null) ? 0 : getFirstLoanAmount().hashCode());
        result = prime * result + ((getLoanRate() == null) ? 0 : getLoanRate().hashCode());
        result = prime * result + ((getFirstLoanRate() == null) ? 0 : getFirstLoanRate().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}