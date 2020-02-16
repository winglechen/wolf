package study.daydayup.wolf.framework.dts.sink;

import lombok.NonNull;
import study.daydayup.wolf.framework.dts.config.SinkConfig;

import java.util.Set;

/**
 * study.daydayup.wolf.framework.dts.sink
 *
 * @author Wingle
 * @since 2020/2/17 12:20 上午
 **/
public abstract class AbstractSink implements Sink {
    protected SinkConfig config;
    protected Long offset;
    protected Long newOffset;

    @Override
    public Set<String> getKeyColumns() {
        return config.getKeyColumns();
    }

    @Override
    public Sink init(SinkConfig config) {
        this.config = config;

        return  this;
    }

    @Override
    public Long getOffset() {
        if (offset != null) {
            return offset;
        }

        offset = config.getSource().getOffset(config.getSinkName());
        return offset;
    }

    @Override
    public boolean isDuplicated(Long id) {
        if (id == null) {
            return true;
        }

        offset = getOffset();
        return id <= offset;
    }
}
