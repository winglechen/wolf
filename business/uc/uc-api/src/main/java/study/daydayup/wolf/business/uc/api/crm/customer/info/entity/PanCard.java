package study.daydayup.wolf.business.uc.api.crm.customer.info.entity;

import lombok.Data;

import study.daydayup.wolf.framework.layer.api.Model;
import java.time.LocalDateTime;

@Data
public class PanCard implements Model {
    private Long id;

    private Long accountId;

    private Long orgId;

    private String panNo;

    private String dob;

    private String name;

    private String fatherName;

    private String frontSide;

    private String backSide;

    private Boolean deleteFlag;

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
        sb.append(", panNo=").append(panNo);
        sb.append(", dob=").append(dob);
        sb.append(", name=").append(name);
        sb.append(", fatherName=").append(fatherName);
        sb.append(", frontSide=").append(frontSide);
        sb.append(", backSide=").append(backSide);
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
        PanCard other = (PanCard) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getPanNo() == null ? other.getPanNo() == null : this.getPanNo().equals(other.getPanNo()))
            && (this.getDob() == null ? other.getDob() == null : this.getDob().equals(other.getDob()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getFatherName() == null ? other.getFatherName() == null : this.getFatherName().equals(other.getFatherName()))
            && (this.getFrontSide() == null ? other.getFrontSide() == null : this.getFrontSide().equals(other.getFrontSide()))
            && (this.getBackSide() == null ? other.getBackSide() == null : this.getBackSide().equals(other.getBackSide()))
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
        result = prime * result + ((getPanNo() == null) ? 0 : getPanNo().hashCode());
        result = prime * result + ((getDob() == null) ? 0 : getDob().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getFatherName() == null) ? 0 : getFatherName().hashCode());
        result = prime * result + ((getFrontSide() == null) ? 0 : getFrontSide().hashCode());
        result = prime * result + ((getBackSide() == null) ? 0 : getBackSide().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}