package study.daydayup.wolf.business.union.task.dts.source;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.union.task.config.ShardingConfig;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.framework.dts.config.SourceConfig;
import study.daydayup.wolf.framework.dts.source.MysqlScanner;
import study.daydayup.wolf.framework.dts.source.MysqlSource;
import study.daydayup.wolf.framework.dts.source.Source;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.task.dts.source
 *
 * @author Wingle
 * @since 2020/2/8 8:27 下午
 **/
@Component
public class ContractSource {
    @Resource
    private ShardingConfig shardingConfig;
    @Resource
    private MysqlSource mysqlSource;

    public Table latest(String sinkName) {

        return null;
    }
}
