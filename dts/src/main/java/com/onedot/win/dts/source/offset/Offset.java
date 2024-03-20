package com.onedot.win.dts.source.offset;

import lombok.*;
import com.onedot.win.common.lang.exception.lang.IllegalArgumentException;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.dts.source.Source;

import java.io.Serializable;

/**
 * com.onedot.win.dts.source.offset
 *
 * @author Wingle
 * @since 2020/9/4 4:43 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Offset implements Serializable {
    private String key;

    private String source;
    private String table;
    private String shard;
    private String sink;

    private Integer dbShard;
    private Integer tableShard;
    private Integer batchSize;

    private Long offset;
    private Long newOffset;

    private Integer version;

    public void increase(long num) {
        if (null == offset) {
            throw new IllegalArgumentException("offset can't be null while increasing");
        }

        newOffset = offset + num;
    }

    public void decrease(long num) {
        if (null == offset) {
            throw new IllegalArgumentException("offset can't be null while decreasing");
        }

        newOffset = offset - num;
        if (newOffset < 0L) {
            newOffset = 0L;
        }
    }

    public String getSource() {
        if (StringUtil.notBlank(source)) {
            return source;
        }

        return "s";
    }

    public String getShard() {
        if (StringUtil.notBlank(shard)) {
            return shard;
        }

        if (null == tableShard) {
            return "df";
        }

        return Integer.toString(tableShard);
    }

    public Offset(@NonNull String taskName) {
        this.key = StringUtil.uuid();
        this.sink = taskName;
    }

    public void mergeSourceConfig(Source config) {
        source = config.getSource();
        table = config.getTable();
        shard = config.getShard();
    }

}
