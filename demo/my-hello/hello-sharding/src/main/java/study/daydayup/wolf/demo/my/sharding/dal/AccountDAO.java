package study.daydayup.wolf.demo.my.sharding.dal;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * study.daydayup.wolf.demo.my.sharding
 *
 * @author Wingle
 * @since 2019/11/13 8:20 下午
 **/
@Repository
public interface AccountDAO {
    @Select("select id, account from account where account_id=#{id}")
    AccountDO getById(long id);
}
