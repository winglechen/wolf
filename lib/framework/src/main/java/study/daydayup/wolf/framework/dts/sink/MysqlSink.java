package study.daydayup.wolf.framework.dts.sink;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.io.db.Statistics;

/**
 * study.daydayup.wolf.framework.dts.sink
 *
 * @author Wingle
 * @since 2020/2/16 6:59 下午
 **/
@Component
public class MysqlSink extends AbstractSink  implements Sink {


    @Override
    public void save(Statistics statistics) {

    }
}
