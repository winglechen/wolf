package study.daydayup.wolf.business.org.biz.task.dal.dataobject;

import java.io.Serializable;
import lombok.Data;

@Data
public class TaskDetailDOWithBLOBs extends TaskDetailDO implements Serializable {
    private String memo;

    private String extendfields;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", memo=").append(memo);
        sb.append(", extendfields=").append(extendfields);
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
        TaskDetailDOWithBLOBs other = (TaskDetailDOWithBLOBs) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getExtendfields() == null ? other.getExtendfields() == null : this.getExtendfields().equals(other.getExtendfields()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getExtendfields() == null) ? 0 : getExtendfields().hashCode());
        return result;
    }
}