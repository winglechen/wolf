package study.daydayup.wolf.bigdata.datav.biz.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ModelCollectorDO implements Serializable {
    private Long id;

    private Long orgId;

    private Long accountId;

    private Integer collectionCount;

    private Integer caseCount;

    private Integer caseAmount;

    private Integer successCount;

    private Integer successAmount;

    private Integer failCount;

    private Integer failAmount;

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
        sb.append(", accountId=").append(accountId);
        sb.append(", collectionCount=").append(collectionCount);
        sb.append(", caseCount=").append(caseCount);
        sb.append(", caseAmount=").append(caseAmount);
        sb.append(", successCount=").append(successCount);
        sb.append(", successAmount=").append(successAmount);
        sb.append(", failCount=").append(failCount);
        sb.append(", failAmount=").append(failAmount);
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
        ModelCollectorDO other = (ModelCollectorDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getCollectionCount() == null ? other.getCollectionCount() == null : this.getCollectionCount().equals(other.getCollectionCount()))
            && (this.getCaseCount() == null ? other.getCaseCount() == null : this.getCaseCount().equals(other.getCaseCount()))
            && (this.getCaseAmount() == null ? other.getCaseAmount() == null : this.getCaseAmount().equals(other.getCaseAmount()))
            && (this.getSuccessCount() == null ? other.getSuccessCount() == null : this.getSuccessCount().equals(other.getSuccessCount()))
            && (this.getSuccessAmount() == null ? other.getSuccessAmount() == null : this.getSuccessAmount().equals(other.getSuccessAmount()))
            && (this.getFailCount() == null ? other.getFailCount() == null : this.getFailCount().equals(other.getFailCount()))
            && (this.getFailAmount() == null ? other.getFailAmount() == null : this.getFailAmount().equals(other.getFailAmount()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getCollectionCount() == null) ? 0 : getCollectionCount().hashCode());
        result = prime * result + ((getCaseCount() == null) ? 0 : getCaseCount().hashCode());
        result = prime * result + ((getCaseAmount() == null) ? 0 : getCaseAmount().hashCode());
        result = prime * result + ((getSuccessCount() == null) ? 0 : getSuccessCount().hashCode());
        result = prime * result + ((getSuccessAmount() == null) ? 0 : getSuccessAmount().hashCode());
        result = prime * result + ((getFailCount() == null) ? 0 : getFailCount().hashCode());
        result = prime * result + ((getFailAmount() == null) ? 0 : getFailAmount().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}