package study.daydayup.wolf.mq.broker.dal.dataobject;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class QueueDO implements Serializable {
    private Integer id;

    private String topic;

    private String consumer;

    private Byte shard;

    private Integer offset;

    private Boolean locker;

    private Date lockedAt;

    private boolean deleteFlag;

    private Date createdAt;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", topic=").append(topic);
        sb.append(", consumer=").append(consumer);
        sb.append(", shard=").append(shard);
        sb.append(", offset=").append(offset);
        sb.append(", locker=").append(locker);
        sb.append(", lockedAt=").append(lockedAt);
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
        QueueDO other = (QueueDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTopic() == null ? other.getTopic() == null : this.getTopic().equals(other.getTopic()))
            && (this.getConsumer() == null ? other.getConsumer() == null : this.getConsumer().equals(other.getConsumer()))
            && (this.getShard() == null ? other.getShard() == null : this.getShard().equals(other.getShard()))
            && (this.getOffset() == null ? other.getOffset() == null : this.getOffset().equals(other.getOffset()))
            && (this.getLocker() == null ? other.getLocker() == null : this.getLocker().equals(other.getLocker()))
            && (this.getLockedAt() == null ? other.getLockedAt() == null : this.getLockedAt().equals(other.getLockedAt()))
            && (this.isDeleteFlag() == other.isDeleteFlag())
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTopic() == null) ? 0 : getTopic().hashCode());
        result = prime * result + ((getConsumer() == null) ? 0 : getConsumer().hashCode());
        result = prime * result + ((getShard() == null) ? 0 : getShard().hashCode());
        result = prime * result + ((getOffset() == null) ? 0 : getOffset().hashCode());
        result = prime * result + ((getLocker() == null) ? 0 : getLocker().hashCode());
        result = prime * result + ((getLockedAt() == null) ? 0 : getLockedAt().hashCode());
        result = prime * result + (isDeleteFlag() ? 1231 : 1237);
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}