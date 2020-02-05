package study.daydayup.wolf.framework.layer.dal.editor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.framework.layer.task.Sink;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.framework.layer.dal.editor
 *
 * @author Wingle
 * @since 2020/2/5 11:25 上午
 **/
@Component("mysql")
public class MysqlEditor implements Sink {

    @Resource
    private JdbcTemplate jdbc;
}
