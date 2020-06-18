package study.daydayup.wolf.business.pay.biz.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BalanceDO implements Serializable {
    private Long id;

    private Long accountId;

    private String accountName;

    private Integer currency;

    private BigDecimal availableAmount;

    private BigDecimal blockedAmount;

    private BigDecimal unavailableAmount;

    private Integer lockerFlag;

    private String locker;

    private LocalDateTime lockedAt;

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
        sb.append(", accountName=").append(accountName);
        sb.append(", currency=").append(currency);
        sb.append(", availableAmount=").append(availableAmount);
        sb.append(", blockedAmount=").append(blockedAmount);
        sb.append(", unavailableAmount=").append(unavailableAmount);
        sb.append(", lockerFlag=").append(lockerFlag);
        sb.append(", locker=").append(locker);
        sb.append(", lockedAt=").append(lockedAt);
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
        BalanceDO other = (BalanceDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getAccountName() == null ? other.getAccountName() == null : this.getAccountName().equals(other.getAccountName()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getAvailableAmount() == null ? other.getAvailableAmount() == null : this.getAvailableAmount().equals(other.getAvailableAmount()))
            && (this.getBlockedAmount() == null ? other.getBlockedAmount() == null : this.getBlockedAmount().equals(other.getBlockedAmount()))
            && (this.getUnavailableAmount() == null ? other.getUnavailableAmount() == null : this.getUnavailableAmount().equals(other.getUnavailableAmount()))
            && (this.getLockerFlag() == null ? other.getLockerFlag() == null : this.getLockerFlag().equals(other.getLockerFlag()))
            && (this.getLocker() == null ? other.getLocker() == null : this.getLocker().equals(other.getLocker()))
            && (this.getLockedAt() == null ? other.getLockedAt() == null : this.getLockedAt().equals(other.getLockedAt()))
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
        result = prime * result + ((getAccountName() == null) ? 0 : getAccountName().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getAvailableAmount() == null) ? 0 : getAvailableAmount().hashCode());
        result = prime * result + ((getBlockedAmount() == null) ? 0 : getBlockedAmount().hashCode());
        result = prime * result + ((getUnavailableAmount() == null) ? 0 : getUnavailableAmount().hashCode());
        result = prime * result + ((getLockerFlag() == null) ? 0 : getLockerFlag().hashCode());
        result = prime * result + ((getLocker() == null) ? 0 : getLocker().hashCode());
        result = prime * result + ((getLockedAt() == null) ? 0 : getLockedAt().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}