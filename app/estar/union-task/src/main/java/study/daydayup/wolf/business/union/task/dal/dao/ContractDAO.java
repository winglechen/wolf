package study.daydayup.wolf.business.union.task.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * study.daydayup.wolf.business.union.task.dal.dao
 *
 * @author Wingle
 * @since 2020/2/4 12:46 上午
 **/
@Mapper
public interface ContractDAO {
    @Select("select * from `contract` order by id desc limit 3")
    List<Map<String, Object>> top();
}
