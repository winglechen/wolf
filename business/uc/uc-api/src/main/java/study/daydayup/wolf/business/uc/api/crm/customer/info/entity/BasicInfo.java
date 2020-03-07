package study.daydayup.wolf.business.uc.api.crm.customer.info.entity;

import lombok.Data;

import study.daydayup.wolf.framework.layer.api.Model;
import java.time.LocalDateTime;

@Data
public class BasicInfo implements Model {
    private Long id;

    private Long accountId;

    private Long orgId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String address;

    private String residenceCity;

    private String gender;

    private String aadhaarNo;

    private String panNo;

    private String email;

    private String postCode;

    private Integer maritalStatus;

    private String occupation;

    private String company;

    private String industry;

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
        sb.append(", firstName=").append(firstName);
        sb.append(", middleName=").append(middleName);
        sb.append(", lastName=").append(lastName);
        sb.append(", address=").append(address);
        sb.append(", residenceCity=").append(residenceCity);
        sb.append(", gender=").append(gender);
        sb.append(", aadhaarNo=").append(aadhaarNo);
        sb.append(", panNo=").append(panNo);
        sb.append(", email=").append(email);
        sb.append(", postCode=").append(postCode);
        sb.append(", maritalStatus=").append(maritalStatus);
        sb.append(", occupation=").append(occupation);
        sb.append(", company=").append(company);
        sb.append(", industry=").append(industry);
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
        BasicInfo other = (BasicInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getFirstName() == null ? other.getFirstName() == null : this.getFirstName().equals(other.getFirstName()))
            && (this.getMiddleName() == null ? other.getMiddleName() == null : this.getMiddleName().equals(other.getMiddleName()))
            && (this.getLastName() == null ? other.getLastName() == null : this.getLastName().equals(other.getLastName()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getResidenceCity() == null ? other.getResidenceCity() == null : this.getResidenceCity().equals(other.getResidenceCity()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getAadhaarNo() == null ? other.getAadhaarNo() == null : this.getAadhaarNo().equals(other.getAadhaarNo()))
            && (this.getPanNo() == null ? other.getPanNo() == null : this.getPanNo().equals(other.getPanNo()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPostCode() == null ? other.getPostCode() == null : this.getPostCode().equals(other.getPostCode()))
            && (this.getMaritalStatus() == null ? other.getMaritalStatus() == null : this.getMaritalStatus().equals(other.getMaritalStatus()))
            && (this.getOccupation() == null ? other.getOccupation() == null : this.getOccupation().equals(other.getOccupation()))
            && (this.getCompany() == null ? other.getCompany() == null : this.getCompany().equals(other.getCompany()))
            && (this.getIndustry() == null ? other.getIndustry() == null : this.getIndustry().equals(other.getIndustry()))
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
        result = prime * result + ((getFirstName() == null) ? 0 : getFirstName().hashCode());
        result = prime * result + ((getMiddleName() == null) ? 0 : getMiddleName().hashCode());
        result = prime * result + ((getLastName() == null) ? 0 : getLastName().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getResidenceCity() == null) ? 0 : getResidenceCity().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getAadhaarNo() == null) ? 0 : getAadhaarNo().hashCode());
        result = prime * result + ((getPanNo() == null) ? 0 : getPanNo().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPostCode() == null) ? 0 : getPostCode().hashCode());
        result = prime * result + ((getMaritalStatus() == null) ? 0 : getMaritalStatus().hashCode());
        result = prime * result + ((getOccupation() == null) ? 0 : getOccupation().hashCode());
        result = prime * result + ((getCompany() == null) ? 0 : getCompany().hashCode());
        result = prime * result + ((getIndustry() == null) ? 0 : getIndustry().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}