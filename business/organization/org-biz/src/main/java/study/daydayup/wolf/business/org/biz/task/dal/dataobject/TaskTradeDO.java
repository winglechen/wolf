package study.daydayup.wolf.business.org.biz.task.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TaskTradeDO implements Serializable {
    private Long id;

    private Long orgId;

    private Long taskId;

    private String tradeNo;

    private Byte installmentNo;

    private Integer currency;

    private BigDecimal amount;

    private Byte tradeType;

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
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", installmentNo=").append(installmentNo);
        sb.append(", currency=").append(currency);
        sb.append(", amount=").append(amount);
        sb.append(", tradeType=").append(tradeType);
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
        TaskTradeDO other = (TaskTradeDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getInstallmentNo() == null ? other.getInstallmentNo() == null : this.getInstallmentNo().equals(other.getInstallmentNo()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getTradeType() == null ? other.getTradeType() == null : this.getTradeType().equals(other.getTradeType()))
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
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getInstallmentNo() == null) ? 0 : getInstallmentNo().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getTradeType() == null) ? 0 : getTradeType().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}