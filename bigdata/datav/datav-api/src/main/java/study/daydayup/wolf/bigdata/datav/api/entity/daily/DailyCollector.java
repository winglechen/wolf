package study.daydayup.wolf.bigdata.datav.api.entity.daily;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DailyCollector implements Model {
    private Long id;

    private Long orgId;

    private Long accountId;

    private LocalDate date;

    private Integer collectionCount;

    private BigDecimal collectionAmount;

    private Integer newCount;

    private BigDecimal newAmount;

    private Integer successCount;

    private BigDecimal successAmount;

    private Integer easySuccessCount;

    private BigDecimal easySuccessAmount;

    private Integer failCount;

    private BigDecimal failAmount;

    private Boolean deleteFlag;

    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orgId=").append(orgId);
        sb.append(", accountId=").append(accountId);
        sb.append(", date=").append(date);
        sb.append(", collectionCount=").append(collectionCount);
        sb.append(", collectionAmount=").append(collectionAmount);
        sb.append(", newCount=").append(newCount);
        sb.append(", newAmount=").append(newAmount);
        sb.append(", successCount=").append(successCount);
        sb.append(", successAmount=").append(successAmount);
        sb.append(", easySuccessCount=").append(easySuccessCount);
        sb.append(", easySuccessAmount=").append(easySuccessAmount);
        sb.append(", failCount=").append(failCount);
        sb.append(", failAmount=").append(failAmount);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", createdAt=").append(createdAt);
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
        DailyCollector other = (DailyCollector) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getCollectionCount() == null ? other.getCollectionCount() == null : this.getCollectionCount().equals(other.getCollectionCount()))
            && (this.getCollectionAmount() == null ? other.getCollectionAmount() == null : this.getCollectionAmount().equals(other.getCollectionAmount()))
            && (this.getNewCount() == null ? other.getNewCount() == null : this.getNewCount().equals(other.getNewCount()))
            && (this.getNewAmount() == null ? other.getNewAmount() == null : this.getNewAmount().equals(other.getNewAmount()))
            && (this.getSuccessCount() == null ? other.getSuccessCount() == null : this.getSuccessCount().equals(other.getSuccessCount()))
            && (this.getSuccessAmount() == null ? other.getSuccessAmount() == null : this.getSuccessAmount().equals(other.getSuccessAmount()))
            && (this.getEasySuccessCount() == null ? other.getEasySuccessCount() == null : this.getEasySuccessCount().equals(other.getEasySuccessCount()))
            && (this.getEasySuccessAmount() == null ? other.getEasySuccessAmount() == null : this.getEasySuccessAmount().equals(other.getEasySuccessAmount()))
            && (this.getFailCount() == null ? other.getFailCount() == null : this.getFailCount().equals(other.getFailCount()))
            && (this.getFailAmount() == null ? other.getFailAmount() == null : this.getFailAmount().equals(other.getFailAmount()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getCollectionCount() == null) ? 0 : getCollectionCount().hashCode());
        result = prime * result + ((getCollectionAmount() == null) ? 0 : getCollectionAmount().hashCode());
        result = prime * result + ((getNewCount() == null) ? 0 : getNewCount().hashCode());
        result = prime * result + ((getNewAmount() == null) ? 0 : getNewAmount().hashCode());
        result = prime * result + ((getSuccessCount() == null) ? 0 : getSuccessCount().hashCode());
        result = prime * result + ((getSuccessAmount() == null) ? 0 : getSuccessAmount().hashCode());
        result = prime * result + ((getEasySuccessCount() == null) ? 0 : getEasySuccessCount().hashCode());
        result = prime * result + ((getEasySuccessAmount() == null) ? 0 : getEasySuccessAmount().hashCode());
        result = prime * result + ((getFailCount() == null) ? 0 : getFailCount().hashCode());
        result = prime * result + ((getFailAmount() == null) ? 0 : getFailAmount().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}