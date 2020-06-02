package study.daydayup.wolf.bigdata.datav.biz.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BeatDO implements Serializable {
    private Long id;

    private Long orgId;

    private Long accountId;

    private String deviceId;

    private String deviceType;

    private String ip;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String eventType;

    private String event;

    private String eventContext;

    private String eventMemo;

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
        sb.append(", deviceId=").append(deviceId);
        sb.append(", deviceType=").append(deviceType);
        sb.append(", ip=").append(ip);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", eventType=").append(eventType);
        sb.append(", event=").append(event);
        sb.append(", eventContext=").append(eventContext);
        sb.append(", eventMemo=").append(eventMemo);
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
        BeatDO other = (BeatDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getDeviceType() == null ? other.getDeviceType() == null : this.getDeviceType().equals(other.getDeviceType()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
            && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
            && (this.getEventType() == null ? other.getEventType() == null : this.getEventType().equals(other.getEventType()))
            && (this.getEvent() == null ? other.getEvent() == null : this.getEvent().equals(other.getEvent()))
            && (this.getEventContext() == null ? other.getEventContext() == null : this.getEventContext().equals(other.getEventContext()))
            && (this.getEventMemo() == null ? other.getEventMemo() == null : this.getEventMemo().equals(other.getEventMemo()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getDeviceType() == null) ? 0 : getDeviceType().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getEventType() == null) ? 0 : getEventType().hashCode());
        result = prime * result + ((getEvent() == null) ? 0 : getEvent().hashCode());
        result = prime * result + ((getEventContext() == null) ? 0 : getEventContext().hashCode());
        result = prime * result + ((getEventMemo() == null) ? 0 : getEventMemo().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}