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
 * @since 2020/6/20 5:28 下午
 **/
@Component
public class VerifyCodeSource {
    @Resource
    private ShardingConfig shardingConfig;
    @Resource
    private MysqlSource mysqlSource;

    public MysqlSource  findLatestCode() {
        SourceConfig sourceConfig = SourceConfig.builder()
                .sourceName("latest-verify-code")
                .tableName("verify_code")
                .columns("id, org_id, created_at")
                .shardingKey(shardingConfig.getShard())
                .build();
        return mysqlSource.init(sourceConfig);
    }
}
