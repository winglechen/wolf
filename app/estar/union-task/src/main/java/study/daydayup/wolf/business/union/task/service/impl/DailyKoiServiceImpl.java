package study.daydayup.wolf.business.union.task.service.impl;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.union.task.dts.sink.DailyKoiSink;
import study.daydayup.wolf.business.union.task.dts.source.UserCreditLogSource;
import study.daydayup.wolf.business.union.task.dts.source.UserSource;
import study.daydayup.wolf.business.union.task.dts.transformation.UserCreditLogTransformation;
import study.daydayup.wolf.business.union.task.dts.transformation.UserTransformation;
import study.daydayup.wolf.business.union.task.service.DailyKoiService;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.dts.sink.MysqlSink;
import study.daydayup.wolf.dts.source.MysqlSource;
import study.daydayup.wolf.dts.transformation.Statistics;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.task.service.impl
 *
 * @author Wingle
 * @since 2020/5/5 10:50 上午
 **/
@Component
public class DailyKoiServiceImpl implements DailyKoiService {

    @Resource
    private UserSource userSource;
    @Resource
    private DailyKoiSink dailyKoiSink;
    @Resource
    private UserTransformation userTransformation;

    @Resource
    private UserCreditLogSource userCreditLogSource;
    @Resource
    private UserCreditLogTransformation userCreditLogTransformation;

    @Override
    public void countPvAndUv() {

    }

    @Override
    public void countRegister() {
        String taskName = "register-count";
        MysqlSource source = userSource.findLatestUser();
        Table stream = source.getStream(taskName);

        MysqlSink sink = dailyKoiSink.create(taskName, source);
        Statistics statistics = userTransformation.latest(stream, sink);
        sink.save(statistics);
    }

    @Override
    public void countIndianInfoState() {
        String taskName = "indian-info-count";
        MysqlSource source = userCreditLogSource.findLatestLog();
        Table stream = source.getStream(taskName);

        MysqlSink sink = dailyKoiSink.create(taskName, source);
        Statistics statistics = userCreditLogTransformation.latest(stream, sink);
        sink.save(statistics);
    }
}
