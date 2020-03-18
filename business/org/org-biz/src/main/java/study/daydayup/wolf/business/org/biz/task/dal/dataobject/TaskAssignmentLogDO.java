package study.daydayup.wolf.business.org.biz.task.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TaskAssignmentLogDO implements Serializable {
    private Long id;

    private Long orgId;
    private Long taskId;
    private Long projectId;

    private Long assigner;
    private Long sourceOwner;
    private Long targetOwner;

    private Integer sourceVersion;
    private Integer targetVersion;

    private String memo;
    private String tags;

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
        sb.append(", taskId=").append(taskId);
        sb.append(", projectId=").append(projectId);
        sb.append(", assigner=").append(assigner);
        sb.append(", sourceOwner=").append(sourceOwner);
        sb.append(", targetOwner=").append(targetOwner);
        sb.append(", sourceVersion=").append(sourceVersion);
        sb.append(", targetVersion=").append(targetVersion);
        sb.append(", memo=").append(memo);
        sb.append(", tags=").append(tags);
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
        TaskAssignmentLogDO other = (TaskAssignmentLogDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getAssigner() == null ? other.getAssigner() == null : this.getAssigner().equals(other.getAssigner()))
            && (this.getSourceOwner() == null ? other.getSourceOwner() == null : this.getSourceOwner().equals(other.getSourceOwner()))
            && (this.getTargetOwner() == null ? other.getTargetOwner() == null : this.getTargetOwner().equals(other.getTargetOwner()))
            && (this.getSourceVersion() == null ? other.getSourceVersion() == null : this.getSourceVersion().equals(other.getSourceVersion()))
            && (this.getTargetVersion() == null ? other.getTargetVersion() == null : this.getTargetVersion().equals(other.getTargetVersion()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
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
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getAssigner() == null) ? 0 : getAssigner().hashCode());
        result = prime * result + ((getSourceOwner() == null) ? 0 : getSourceOwner().hashCode());
        result = prime * result + ((getTargetOwner() == null) ? 0 : getTargetOwner().hashCode());
        result = prime * result + ((getSourceVersion() == null) ? 0 : getSourceVersion().hashCode());
        result = prime * result + ((getTargetVersion() == null) ? 0 : getTargetVersion().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getEditor() == null) ? 0 : getEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}