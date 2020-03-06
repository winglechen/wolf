package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PassportDO implements Serializable {
    private Long id;

    private Long accountId;

    private Long orgId;

    private String passportNo;

    private String type;

    private String countryCode;

    private String givenName;

    private String nationality;

    private String gender;

    private String dob;

    private String birthPlace;

    private String issuePlace;

    private String issueDate;

    private String expiryDate;

    private String fatherName;

    private String motherName;

    private String spouseName;

    private String address;

    private String frontSide;

    private String backSide;

    private Integer version;

    private String fileNo;

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
        sb.append(", passportNo=").append(passportNo);
        sb.append(", type=").append(type);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", givenName=").append(givenName);
        sb.append(", nationality=").append(nationality);
        sb.append(", gender=").append(gender);
        sb.append(", dob=").append(dob);
        sb.append(", birthPlace=").append(birthPlace);
        sb.append(", issuePlace=").append(issuePlace);
        sb.append(", issueDate=").append(issueDate);
        sb.append(", expiryDate=").append(expiryDate);
        sb.append(", fatherName=").append(fatherName);
        sb.append(", motherName=").append(motherName);
        sb.append(", spouseName=").append(spouseName);
        sb.append(", address=").append(address);
        sb.append(", frontSide=").append(frontSide);
        sb.append(", backSide=").append(backSide);
        sb.append(", version=").append(version);
        sb.append(", fileNo=").append(fileNo);
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
        PassportDO other = (PassportDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getPassportNo() == null ? other.getPassportNo() == null : this.getPassportNo().equals(other.getPassportNo()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCountryCode() == null ? other.getCountryCode() == null : this.getCountryCode().equals(other.getCountryCode()))
            && (this.getGivenName() == null ? other.getGivenName() == null : this.getGivenName().equals(other.getGivenName()))
            && (this.getNationality() == null ? other.getNationality() == null : this.getNationality().equals(other.getNationality()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getDob() == null ? other.getDob() == null : this.getDob().equals(other.getDob()))
            && (this.getBirthPlace() == null ? other.getBirthPlace() == null : this.getBirthPlace().equals(other.getBirthPlace()))
            && (this.getIssuePlace() == null ? other.getIssuePlace() == null : this.getIssuePlace().equals(other.getIssuePlace()))
            && (this.getIssueDate() == null ? other.getIssueDate() == null : this.getIssueDate().equals(other.getIssueDate()))
            && (this.getExpiryDate() == null ? other.getExpiryDate() == null : this.getExpiryDate().equals(other.getExpiryDate()))
            && (this.getFatherName() == null ? other.getFatherName() == null : this.getFatherName().equals(other.getFatherName()))
            && (this.getMotherName() == null ? other.getMotherName() == null : this.getMotherName().equals(other.getMotherName()))
            && (this.getSpouseName() == null ? other.getSpouseName() == null : this.getSpouseName().equals(other.getSpouseName()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getFrontSide() == null ? other.getFrontSide() == null : this.getFrontSide().equals(other.getFrontSide()))
            && (this.getBackSide() == null ? other.getBackSide() == null : this.getBackSide().equals(other.getBackSide()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getFileNo() == null ? other.getFileNo() == null : this.getFileNo().equals(other.getFileNo()))
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
        result = prime * result + ((getPassportNo() == null) ? 0 : getPassportNo().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCountryCode() == null) ? 0 : getCountryCode().hashCode());
        result = prime * result + ((getGivenName() == null) ? 0 : getGivenName().hashCode());
        result = prime * result + ((getNationality() == null) ? 0 : getNationality().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getDob() == null) ? 0 : getDob().hashCode());
        result = prime * result + ((getBirthPlace() == null) ? 0 : getBirthPlace().hashCode());
        result = prime * result + ((getIssuePlace() == null) ? 0 : getIssuePlace().hashCode());
        result = prime * result + ((getIssueDate() == null) ? 0 : getIssueDate().hashCode());
        result = prime * result + ((getExpiryDate() == null) ? 0 : getExpiryDate().hashCode());
        result = prime * result + ((getFatherName() == null) ? 0 : getFatherName().hashCode());
        result = prime * result + ((getMotherName() == null) ? 0 : getMotherName().hashCode());
        result = prime * result + ((getSpouseName() == null) ? 0 : getSpouseName().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getFrontSide() == null) ? 0 : getFrontSide().hashCode());
        result = prime * result + ((getBackSide() == null) ? 0 : getBackSide().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getFileNo() == null) ? 0 : getFileNo().hashCode());
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}