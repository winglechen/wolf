package study.daydayup.wolf.framework.dts.source;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.framework.dts.offset.Offset;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.framework.dts.source
 *
 * @author Wingle
 * @since 2020/2/16 6:12 下午
 **/
@Component
public class MysqlSource extends AbstractSource implements Source {
    @Resource
    private Offset offset;

    @Override
    public Table getStream() {
        return null;
    }

    @Override
    public void saveOffset(String sinkName, Long offset) {

    }
}
