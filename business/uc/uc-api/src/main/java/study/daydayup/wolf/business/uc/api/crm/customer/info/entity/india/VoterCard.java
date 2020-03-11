package study.daydayup.wolf.business.uc.api.crm.customer.info.entity.india;

import lombok.Data;

import study.daydayup.wolf.framework.layer.api.Model;
import java.time.LocalDateTime;

@Data
public class VoterCard implements Model {
    private Long id;

    private Long accountId;

    private Long orgId;

    private String voterNo;

    private String name;

    private String fatherName;

    private String address;

    private String frontSide;

    private String backSide;

    private String signDate;

    private Integer deleteFlag;

    private Integer version;

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
        sb.append(", accountId=").append(accountId);
        sb.append(", orgId=").append(orgId);
        sb.append(", voterNo=").append(voterNo);
        sb.append(", name=").append(name);
        sb.append(", fatherName=").append(fatherName);
        sb.append(", address=").append(address);
        sb.append(", frontSide=").append(frontSide);
        sb.append(", backSide=").append(backSide);
        sb.append(", signDate=").append(signDate);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", version=").append(version);
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
        VoterCard other = (VoterCard) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getVoterNo() == null ? other.getVoterNo() == null : this.getVoterNo().equals(other.getVoterNo()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getFatherName() == null ? other.getFatherName() == null : this.getFatherName().equals(other.getFatherName()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getFrontSide() == null ? other.getFrontSide() == null : this.getFrontSide().equals(other.getFrontSide()))
            && (this.getBackSide() == null ? other.getBackSide() == null : this.getBackSide().equals(other.getBackSide()))
            && (this.getSignDate() == null ? other.getSignDate() == null : this.getSignDate().equals(other.getSignDate()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getLastEditor() == null ? other.getLastEditor() == null : this.getLastEditor().equals(other.getLastEditor()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getVoterNo() == null) ? 0 : getVoterNo().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getFatherName() == null) ? 0 : getFatherName().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getFrontSide() == null) ? 0 : getFrontSide().hashCode());
        result = prime * result + ((getBackSide() == null) ? 0 : getBackSide().hashCode());
        result = prime * result + ((getSignDate() == null) ? 0 : getSignDate().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}