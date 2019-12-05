package study.daydayup.wolf.mq.broker.dal.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * study.daydayup.wolf.mq.broker.dal.dao
 *
 * @author Wingle
 * @since 2019/12/5 9:12 上午
 **/
public class MessageDAOTest {
    private static MessageDAO mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(MessageDAOTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/MessageDAOTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(MessageDAO.class, builder.openSession(true));
    }

    @Test
    public void testSelectByIds() throws FileNotFoundException {
        mapper.selectByIds();
    }
}
