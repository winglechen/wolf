package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserCreditDO implements Serializable {
    private Long id;

    private Long accountId;

    private Long orgId;

    private Long creditAmount;

    private String tags;

    private Integer basicInfoAuthStatus;

    private Integer livenessAuthStatus;

    private Integer aadhaarAuthStatus;

    private Integer panAuthStatus;

    private Integer passportAuthStatus;

    private Integer dlAuthStatus;

    private Integer voterCardAuthStatus;

    private Integer bankCardBindStatus;

    private Integer kycStatus;

    private Integer version;

    private Boolean deleteFlag;

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
        sb.append(", creditAmount=").append(creditAmount);
        sb.append(", tags=").append(tags);
        sb.append(", basicInfoAuthStatus=").append(basicInfoAuthStatus);
        sb.append(", livenessAuthStatus=").append(livenessAuthStatus);
        sb.append(", aadhaarAuthStatus=").append(aadhaarAuthStatus);
        sb.append(", panAuthStatus=").append(panAuthStatus);
        sb.append(", passportAuthStatus=").append(passportAuthStatus);
        sb.append(", dlAuthStatus=").append(dlAuthStatus);
        sb.append(", voterCardAuthStatus=").append(voterCardAuthStatus);
        sb.append(", bankCardBindStatus=").append(bankCardBindStatus);
        sb.append(", kycStatus=").append(kycStatus);
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
        UserCreditDO other = (UserCreditDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getCreditAmount() == null ? other.getCreditAmount() == null : this.getCreditAmount().equals(other.getCreditAmount()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getBasicInfoAuthStatus() == null ? other.getBasicInfoAuthStatus() == null : this.getBasicInfoAuthStatus().equals(other.getBasicInfoAuthStatus()))
            && (this.getLivenessAuthStatus() == null ? other.getLivenessAuthStatus() == null : this.getLivenessAuthStatus().equals(other.getLivenessAuthStatus()))
            && (this.getAadhaarAuthStatus() == null ? other.getAadhaarAuthStatus() == null : this.getAadhaarAuthStatus().equals(other.getAadhaarAuthStatus()))
            && (this.getPanAuthStatus() == null ? other.getPanAuthStatus() == null : this.getPanAuthStatus().equals(other.getPanAuthStatus()))
            && (this.getPassportAuthStatus() == null ? other.getPassportAuthStatus() == null : this.getPassportAuthStatus().equals(other.getPassportAuthStatus()))
            && (this.getDlAuthStatus() == null ? other.getDlAuthStatus() == null : this.getDlAuthStatus().equals(other.getDlAuthStatus()))
            && (this.getVoterCardAuthStatus() == null ? other.getVoterCardAuthStatus() == null : this.getVoterCardAuthStatus().equals(other.getVoterCardAuthStatus()))
            && (this.getBankCardBindStatus() == null ? other.getBankCardBindStatus() == null : this.getBankCardBindStatus().equals(other.getBankCardBindStatus()))
            && (this.getKycStatus() == null ? other.getKycStatus() == null : this.getKycStatus().equals(other.getKycStatus()))
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
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getCreditAmount() == null) ? 0 : getCreditAmount().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getBasicInfoAuthStatus() == null) ? 0 : getBasicInfoAuthStatus().hashCode());
        result = prime * result + ((getLivenessAuthStatus() == null) ? 0 : getLivenessAuthStatus().hashCode());
        result = prime * result + ((getAadhaarAuthStatus() == null) ? 0 : getAadhaarAuthStatus().hashCode());
        result = prime * result + ((getPanAuthStatus() == null) ? 0 : getPanAuthStatus().hashCode());
        result = prime * result + ((getPassportAuthStatus() == null) ? 0 : getPassportAuthStatus().hashCode());
        result = prime * result + ((getDlAuthStatus() == null) ? 0 : getDlAuthStatus().hashCode());
        result = prime * result + ((getVoterCardAuthStatus() == null) ? 0 : getVoterCardAuthStatus().hashCode());
        result = prime * result + ((getBankCardBindStatus() == null) ? 0 : getBankCardBindStatus().hashCode());
        result = prime * result + ((getKycStatus() == null) ? 0 : getKycStatus().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}