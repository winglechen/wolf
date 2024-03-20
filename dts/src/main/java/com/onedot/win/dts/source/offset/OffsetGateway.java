package com.onedot.win.dts.source.offset;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * com.onedot.win.framework.layer.dal.scanner
 *
 * @author Wingle
 * @since 2020/2/5 5:32 下午
 **/
@Component
public class OffsetGateway implements OffsetHolder {
    @Resource
    private MysqlOffsetHolder mysqlOffsetHolder;

    @Override
    public Long get(Offset offset) {
        Long id = mysqlOffsetHolder.get(offset);
        offset.setOffset(id);

        return id;
    }

    @Override
    public Long read(Offset offset) {
        Long id = mysqlOffsetHolder.read(offset);
        offset.setOffset(id);

        return id;
    }

    @Override
    public int set(Offset offset) {
        return mysqlOffsetHolder.set(offset);
    }

    @Override
    public void lock(Offset offset) {
        mysqlOffsetHolder.lock(offset);
    }

    @Override
    public Long increase(Offset offset, long num) {
        return mysqlOffsetHolder.increase(offset, num);
    }

    @Override
    public Long decrease(Offset offset, long num) {
        return mysqlOffsetHolder.decrease(offset, num);
    }

    @Override
    public Long lockAndIncrease(Offset offset, long num) {
        return mysqlOffsetHolder.lockAndIncrease(offset, num);
    }

    @Override
    public Long lockAndDecrease(Offset offset, long num) {
        return mysqlOffsetHolder.lockAndDecrease(offset, num);
    }


}
