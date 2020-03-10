package study.daydayup.wolf.business.uc.api.crm.customer.credit.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreditLine implements Model {
    private Long id;

    private Long orgId;

    private Long accountId;

    private BigDecimal amount;

    private Integer currency;

    private Integer timesLatestDay;

    private Integer timesLatestWeek;

    private Integer timesLatestMonth;

    private Integer timesLatestYear;

    private LocalDateTime promotedAt;

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
        sb.append(", accountId=").append(accountId);
        sb.append(", amount=").append(amount);
        sb.append(", currency=").append(currency);
        sb.append(", timesLatestDay=").append(timesLatestDay);
        sb.append(", timesLatestWeek=").append(timesLatestWeek);
        sb.append(", timesLatestMonth=").append(timesLatestMonth);
        sb.append(", timesLatestYear=").append(timesLatestYear);
        sb.append(", promotedAt=").append(promotedAt);
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
        CreditLine other = (CreditLine) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getTimesLatestDay() == null ? other.getTimesLatestDay() == null : this.getTimesLatestDay().equals(other.getTimesLatestDay()))
            && (this.getTimesLatestWeek() == null ? other.getTimesLatestWeek() == null : this.getTimesLatestWeek().equals(other.getTimesLatestWeek()))
            && (this.getTimesLatestMonth() == null ? other.getTimesLatestMonth() == null : this.getTimesLatestMonth().equals(other.getTimesLatestMonth()))
            && (this.getTimesLatestYear() == null ? other.getTimesLatestYear() == null : this.getTimesLatestYear().equals(other.getTimesLatestYear()))
            && (this.getPromotedAt() == null ? other.getPromotedAt() == null : this.getPromotedAt().equals(other.getPromotedAt()))
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
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getTimesLatestDay() == null) ? 0 : getTimesLatestDay().hashCode());
        result = prime * result + ((getTimesLatestWeek() == null) ? 0 : getTimesLatestWeek().hashCode());
        result = prime * result + ((getTimesLatestMonth() == null) ? 0 : getTimesLatestMonth().hashCode());
        result = prime * result + ((getTimesLatestYear() == null) ? 0 : getTimesLatestYear().hashCode());
        result = prime * result + ((getPromotedAt() == null) ? 0 : getPromotedAt().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}