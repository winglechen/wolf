package study.daydayup.wolf.business.union.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import study.daydayup.wolf.framework.util.BeanUtil;

/**
 * study.daydayup.wolf.business.union.task
 *
 * @author Wingle
 * @since 2020/2/3 7:43 下午
 **/
@SpringBootApplication
@MapperScan("study.daydayup.wolf.business.union.task.dal.dao")
public class UnionTaskApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(UnionTaskApplication.class, args);

        BeanUtil.init(applicationContext);
    }
}
