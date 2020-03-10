package study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CreditLogDO implements Serializable {
    private Long id;

    private Long orgId;

    private Long accountId;

    private Integer operationType;

    private BigDecimal sourceAmount;

    private BigDecimal targetAmount;

    private String tags;

    private String memo;

    private Integer sourceVersion;

    private Integer targetVersion;

    private Long editor;

    private Integer deleteFlag;

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
        sb.append(", operationType=").append(operationType);
        sb.append(", sourceAmount=").append(sourceAmount);
        sb.append(", targetAmount=").append(targetAmount);
        sb.append(", tags=").append(tags);
        sb.append(", memo=").append(memo);
        sb.append(", sourceVersion=").append(sourceVersion);
        sb.append(", targetVersion=").append(targetVersion);
        sb.append(", editor=").append(editor);
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
        CreditLogDO other = (CreditLogDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getOperationType() == null ? other.getOperationType() == null : this.getOperationType().equals(other.getOperationType()))
            && (this.getSourceAmount() == null ? other.getSourceAmount() == null : this.getSourceAmount().equals(other.getSourceAmount()))
            && (this.getTargetAmount() == null ? other.getTargetAmount() == null : this.getTargetAmount().equals(other.getTargetAmount()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getSourceVersion() == null ? other.getSourceVersion() == null : this.getSourceVersion().equals(other.getSourceVersion()))
            && (this.getTargetVersion() == null ? other.getTargetVersion() == null : this.getTargetVersion().equals(other.getTargetVersion()))
            && (this.getEditor() == null ? other.getEditor() == null : this.getEditor().equals(other.getEditor()))
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
        result = prime * result + ((getOperationType() == null) ? 0 : getOperationType().hashCode());
        result = prime * result + ((getSourceAmount() == null) ? 0 : getSourceAmount().hashCode());
        result = prime * result + ((getTargetAmount() == null) ? 0 : getTargetAmount().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getSourceVersion() == null) ? 0 : getSourceVersion().hashCode());
        result = prime * result + ((getTargetVersion() == null) ? 0 : getTargetVersion().hashCode());
        result = prime * result + ((getEditor() == null) ? 0 : getEditor().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}