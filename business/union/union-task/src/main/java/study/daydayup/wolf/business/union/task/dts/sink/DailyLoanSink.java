package study.daydayup.wolf.business.union.task.dts.sink;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.io.db.Statistics;
import study.daydayup.wolf.framework.dts.sink.Sink;

/**
 * study.daydayup.wolf.business.union.task.dts.sink
 *
 * @author Wingle
 * @since 2020/2/8 8:26 下午
 **/
@Component
public class DailyLoanSink implements Sink {
    public void save(@NonNull Statistics statistics) {

    }
}
