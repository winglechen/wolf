package study.daydayup.wolf.business.union.task.service.impl;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.union.task.dts.sink.DailySmsSink;
import study.daydayup.wolf.business.union.task.dts.source.VerifyCodeSource;
import study.daydayup.wolf.business.union.task.dts.transformation.VerifyCodeTransformation;
import study.daydayup.wolf.business.union.task.service.DailySmsService;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.dts.sink.MysqlSink;
import study.daydayup.wolf.dts.source.MysqlSource;
import study.daydayup.wolf.dts.transformation.Statistics;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.task.service.impl
 *
 * @author Wingle
 * @since 2020/6/20 5:26 下午
 **/
@Component
public class DailySmsServiceImpl implements DailySmsService {
    @Resource
    private VerifyCodeSource verifyCodeSource;
    @Resource
    private DailySmsSink dailySmsSink;
    @Resource
    private VerifyCodeTransformation verifyCodeTransformation;

    @Override
    public void countVerifyCodeSms() {
        String taskName = "verify-code-count";
        MysqlSource source = verifyCodeSource.findLatestCode();
        Table stream = source.getStream(taskName);

        MysqlSink sink = dailySmsSink.create(taskName, source);
        Statistics statistics = verifyCodeTransformation.latest(stream, sink);
        sink.save(statistics);
    }
}
