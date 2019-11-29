package study.daydayup.wolf.mq.broker.dal.dataobject;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class TaskDO implements Serializable {
    private Long id;

    private String topic;

    private Byte partition;

    private String consumer;

    private String messageId;

    private Integer offset;

    private Byte state;

    private Date createdAt;

    private Date doneAt;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", topic=").append(topic);
        sb.append(", partition=").append(partition);
        sb.append(", consumer=").append(consumer);
        sb.append(", messageId=").append(messageId);
        sb.append(", offset=").append(offset);
        sb.append(", state=").append(state);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", doneAt=").append(doneAt);
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
        TaskDO other = (TaskDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTopic() == null ? other.getTopic() == null : this.getTopic().equals(other.getTopic()))
            && (this.getPartition() == null ? other.getPartition() == null : this.getPartition().equals(other.getPartition()))
            && (this.getConsumer() == null ? other.getConsumer() == null : this.getConsumer().equals(other.getConsumer()))
            && (this.getMessageId() == null ? other.getMessageId() == null : this.getMessageId().equals(other.getMessageId()))
            && (this.getOffset() == null ? other.getOffset() == null : this.getOffset().equals(other.getOffset()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getDoneAt() == null ? other.getDoneAt() == null : this.getDoneAt().equals(other.getDoneAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTopic() == null) ? 0 : getTopic().hashCode());
        result = prime * result + ((getPartition() == null) ? 0 : getPartition().hashCode());
        result = prime * result + ((getConsumer() == null) ? 0 : getConsumer().hashCode());
        result = prime * result + ((getMessageId() == null) ? 0 : getMessageId().hashCode());
        result = prime * result + ((getOffset() == null) ? 0 : getOffset().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getDoneAt() == null) ? 0 : getDoneAt().hashCode());
        return result;
    }
}