package study.daydayup.wolf.business.org.biz.task.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TaskDO implements Serializable {
    private Long id;

    private Long orgId;

    private Long staffId;

    private Long projectId;

    private Long parentId;

    private String name;

    private Integer taskType;

    private Integer state;

    private Integer priority;

    private BigDecimal progressRate;

    private Integer workLoad;

    private Integer storyPoints;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private String tags;

    private Integer version;

    private Integer deleteFlag;

    private Long lastEditor;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

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
        sb.append(", projectId=").append(projectId);
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", taskType=").append(taskType);
        sb.append(", state=").append(state);
        sb.append(", priority=").append(priority);
        sb.append(", progressRate=").append(progressRate);
        sb.append(", workLoad=").append(workLoad);
        sb.append(", storyPoints=").append(storyPoints);
        sb.append(", startAt=").append(startAt);
        sb.append(", endAt=").append(endAt);
        sb.append(", tags=").append(tags);
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
        TaskDO other = (TaskDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getStaffId() == null ? other.getStaffId() == null : this.getStaffId().equals(other.getStaffId()))
            && (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTaskType() == null ? other.getTaskType() == null : this.getTaskType().equals(other.getTaskType()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
            && (this.getProgressRate() == null ? other.getProgressRate() == null : this.getProgressRate().equals(other.getProgressRate()))
            && (this.getWorkLoad() == null ? other.getWorkLoad() == null : this.getWorkLoad().equals(other.getWorkLoad()))
            && (this.getStoryPoints() == null ? other.getStoryPoints() == null : this.getStoryPoints().equals(other.getStoryPoints()))
            && (this.getStartAt() == null ? other.getStartAt() == null : this.getStartAt().equals(other.getStartAt()))
            && (this.getEndAt() == null ? other.getEndAt() == null : this.getEndAt().equals(other.getEndAt()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getLastEditor() == null ? other.getLastEditor() == null : this.getLastEditor().equals(other.getLastEditor()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getStaffId() == null) ? 0 : getStaffId().hashCode());
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTaskType() == null) ? 0 : getTaskType().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getProgressRate() == null) ? 0 : getProgressRate().hashCode());
        result = prime * result + ((getWorkLoad() == null) ? 0 : getWorkLoad().hashCode());
        result = prime * result + ((getStoryPoints() == null) ? 0 : getStoryPoints().hashCode());
        result = prime * result + ((getStartAt() == null) ? 0 : getStartAt().hashCode());
        result = prime * result + ((getEndAt() == null) ? 0 : getEndAt().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}