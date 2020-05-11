package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserDO implements Serializable {
    private Long id;

    private Long accountId;

    private Long orgId;

    private Long channelId;

    private String nickname;

    private String avatar;

    private String realName;

    private String mobile;

    private String email;

    private String aadhaarNo;

    private String panNo;

    private String gender;

    private String dob;

    private Long creditAmount;

    private String tags;

    private String source;

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
        sb.append(", channelId=").append(channelId);
        sb.append(", nickname=").append(nickname);
        sb.append(", avatar=").append(avatar);
        sb.append(", realName=").append(realName);
        sb.append(", mobile=").append(mobile);
        sb.append(", email=").append(email);
        sb.append(", aadhaarNo=").append(aadhaarNo);
        sb.append(", panNo=").append(panNo);
        sb.append(", gender=").append(gender);
        sb.append(", dob=").append(dob);
        sb.append(", creditAmount=").append(creditAmount);
        sb.append(", tags=").append(tags);
        sb.append(", source=").append(source);
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
        UserDO other = (UserDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getChannelId() == null ? other.getChannelId() == null : this.getChannelId().equals(other.getChannelId()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
            && (this.getRealName() == null ? other.getRealName() == null : this.getRealName().equals(other.getRealName()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getAadhaarNo() == null ? other.getAadhaarNo() == null : this.getAadhaarNo().equals(other.getAadhaarNo()))
            && (this.getPanNo() == null ? other.getPanNo() == null : this.getPanNo().equals(other.getPanNo()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getDob() == null ? other.getDob() == null : this.getDob().equals(other.getDob()))
            && (this.getCreditAmount() == null ? other.getCreditAmount() == null : this.getCreditAmount().equals(other.getCreditAmount()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
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
        result = prime * result + ((getChannelId() == null) ? 0 : getChannelId().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getRealName() == null) ? 0 : getRealName().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getAadhaarNo() == null) ? 0 : getAadhaarNo().hashCode());
        result = prime * result + ((getPanNo() == null) ? 0 : getPanNo().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getDob() == null) ? 0 : getDob().hashCode());
        result = prime * result + ((getCreditAmount() == null) ? 0 : getCreditAmount().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
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