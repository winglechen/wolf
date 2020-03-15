package study.daydayup.wolf.business.org.biz.task.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TaskContactDO implements Serializable {
    private Long id;

    private Long orgId;

    private Long taskId;

    private Long partnerOrgId;

    private Long customerId;

    private Integer contactPerson;

    private Integer contactMethod;

    private Integer contactState;

    private Integer contactResult;

    private String contactAttachment;

    private String resultAttachment;

    private LocalDateTime recontactAt;

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
        sb.append(", taskId=").append(taskId);
        sb.append(", partnerOrgId=").append(partnerOrgId);
        sb.append(", customerId=").append(customerId);
        sb.append(", contactPerson=").append(contactPerson);
        sb.append(", contactMethod=").append(contactMethod);
        sb.append(", contactState=").append(contactState);
        sb.append(", contactResult=").append(contactResult);
        sb.append(", contactAttachment=").append(contactAttachment);
        sb.append(", resultAttachment=").append(resultAttachment);
        sb.append(", recontactAt=").append(recontactAt);
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
        TaskContactDO other = (TaskContactDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getPartnerOrgId() == null ? other.getPartnerOrgId() == null : this.getPartnerOrgId().equals(other.getPartnerOrgId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getContactPerson() == null ? other.getContactPerson() == null : this.getContactPerson().equals(other.getContactPerson()))
            && (this.getContactMethod() == null ? other.getContactMethod() == null : this.getContactMethod().equals(other.getContactMethod()))
            && (this.getContactState() == null ? other.getContactState() == null : this.getContactState().equals(other.getContactState()))
            && (this.getContactResult() == null ? other.getContactResult() == null : this.getContactResult().equals(other.getContactResult()))
            && (this.getContactAttachment() == null ? other.getContactAttachment() == null : this.getContactAttachment().equals(other.getContactAttachment()))
            && (this.getResultAttachment() == null ? other.getResultAttachment() == null : this.getResultAttachment().equals(other.getResultAttachment()))
            && (this.getRecontactAt() == null ? other.getRecontactAt() == null : this.getRecontactAt().equals(other.getRecontactAt()))
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
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getPartnerOrgId() == null) ? 0 : getPartnerOrgId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getContactPerson() == null) ? 0 : getContactPerson().hashCode());
        result = prime * result + ((getContactMethod() == null) ? 0 : getContactMethod().hashCode());
        result = prime * result + ((getContactState() == null) ? 0 : getContactState().hashCode());
        result = prime * result + ((getContactResult() == null) ? 0 : getContactResult().hashCode());
        result = prime * result + ((getContactAttachment() == null) ? 0 : getContactAttachment().hashCode());
        result = prime * result + ((getResultAttachment() == null) ? 0 : getResultAttachment().hashCode());
        result = prime * result + ((getRecontactAt() == null) ? 0 : getRecontactAt().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}