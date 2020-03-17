package study.daydayup.wolf.business.org.biz.task.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TaskStateLogDO implements Serializable {
    private Long id;

    private Long orgId;

    private Long staffId;

    private Long taskId;

    private Long projectId;

    private Short sourceState;

    private Short targetState;

    private Integer sourceVersion;

    private Integer targetVersion;

    private Integer deleteFlag;

    private Long editor;

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
        sb.append(", staffId=").append(staffId);
        sb.append(", taskId=").append(taskId);
        sb.append(", projectId=").append(projectId);
        sb.append(", sourceState=").append(sourceState);
        sb.append(", targetState=").append(targetState);
        sb.append(", sourceVersion=").append(sourceVersion);
        sb.append(", targetVersion=").append(targetVersion);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", editor=").append(editor);
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
        TaskStateLogDO other = (TaskStateLogDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getStaffId() == null ? other.getStaffId() == null : this.getStaffId().equals(other.getStaffId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getSourceState() == null ? other.getSourceState() == null : this.getSourceState().equals(other.getSourceState()))
            && (this.getTargetState() == null ? other.getTargetState() == null : this.getTargetState().equals(other.getTargetState()))
            && (this.getSourceVersion() == null ? other.getSourceVersion() == null : this.getSourceVersion().equals(other.getSourceVersion()))
            && (this.getTargetVersion() == null ? other.getTargetVersion() == null : this.getTargetVersion().equals(other.getTargetVersion()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getEditor() == null ? other.getEditor() == null : this.getEditor().equals(other.getEditor()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getStaffId() == null) ? 0 : getStaffId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getSourceState() == null) ? 0 : getSourceState().hashCode());
        result = prime * result + ((getTargetState() == null) ? 0 : getTargetState().hashCode());
        result = prime * result + ((getSourceVersion() == null) ? 0 : getSourceVersion().hashCode());
        result = prime * result + ((getTargetVersion() == null) ? 0 : getTargetVersion().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getEditor() == null) ? 0 : getEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}