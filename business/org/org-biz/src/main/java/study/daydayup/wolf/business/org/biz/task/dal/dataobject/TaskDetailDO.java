package study.daydayup.wolf.business.org.biz.task.dal.dataobject;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskDetailDO implements Serializable {
    private Long id;

    private Long orgId;

    private String memo;

    private String extendFields;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orgId=").append(orgId);
        sb.append(", memo=").append(memo);
        sb.append(", extendFields=").append(extendFields);
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
        TaskDetailDO other = (TaskDetailDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getExtendFields() == null ? other.getExtendFields() == null : this.getExtendFields().equals(other.getExtendFields()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getExtendFields() == null) ? 0 : getExtendFields().hashCode());
        return result;
    }
}