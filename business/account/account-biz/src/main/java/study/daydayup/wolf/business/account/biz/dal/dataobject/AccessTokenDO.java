package study.daydayup.wolf.business.account.biz.dal.dataobject;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class AccessTokenDO implements Serializable {
    private Long id;

    private Long accountId;

    private String clientId;

    private String accessToken;

    private String refreshToken;

    private Date expiredAt;

    private Date refreshExpiredAt;

    private Integer version;

    private boolean deleteFlag;

    private Long lastEditor;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", accountId=").append(accountId);
        sb.append(", clientId=").append(clientId);
        sb.append(", accessToken=").append(accessToken);
        sb.append(", refreshToken=").append(refreshToken);
        sb.append(", expiredAt=").append(expiredAt);
        sb.append(", refreshExpiredAt=").append(refreshExpiredAt);
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
        AccessTokenDO other = (AccessTokenDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getClientId() == null ? other.getClientId() == null : this.getClientId().equals(other.getClientId()))
            && (this.getAccessToken() == null ? other.getAccessToken() == null : this.getAccessToken().equals(other.getAccessToken()))
            && (this.getRefreshToken() == null ? other.getRefreshToken() == null : this.getRefreshToken().equals(other.getRefreshToken()))
            && (this.getExpiredAt() == null ? other.getExpiredAt() == null : this.getExpiredAt().equals(other.getExpiredAt()))
            && (this.getRefreshExpiredAt() == null ? other.getRefreshExpiredAt() == null : this.getRefreshExpiredAt().equals(other.getRefreshExpiredAt()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.isDeleteFlag() == other.isDeleteFlag())
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
        result = prime * result + ((getClientId() == null) ? 0 : getClientId().hashCode());
        result = prime * result + ((getAccessToken() == null) ? 0 : getAccessToken().hashCode());
        result = prime * result + ((getRefreshToken() == null) ? 0 : getRefreshToken().hashCode());
        result = prime * result + ((getExpiredAt() == null) ? 0 : getExpiredAt().hashCode());
        result = prime * result + ((getRefreshExpiredAt() == null) ? 0 : getRefreshExpiredAt().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + (isDeleteFlag() ? 1231 : 1237);
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}