package study.daydayup.wolf.business.union.task.dts.source;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.union.task.config.ShardingConfig;
import study.daydayup.wolf.dts.config.SourceConfig;
import study.daydayup.wolf.dts.source.MysqlSource;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.task.dts.source
 *
 * @author Wingle
 * @since 2020/5/5 11:22 上午
 **/
@Component
public class UserCreditLogSource {
    @Resource
    private ShardingConfig shardingConfig;
    @Resource
    private MysqlSource mysqlSource;

    public MysqlSource  findLatestLog() {
        SourceConfig sourceConfig = SourceConfig.builder()
                .sourceName("latest-credit-log")
                .tableName("user")
                .columns("id, org_id, account_id, auth_type, status, created_at")
                .shardingKey(shardingConfig.getShard())
                .build();
        mysqlSource.init(sourceConfig);

        return mysqlSource;
    }
}
