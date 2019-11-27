package study.daydayup.wolf.demo.my.gen.dal.dao.auto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import study.daydayup.wolf.demo.my.gen.dal.dataobject.StudentDO;
import study.daydayup.wolf.demo.my.gen.dal.dataobject.StudentDOExample;

public interface StudentDAO {
    long countByExample(StudentDOExample example);

    int deleteByExample(StudentDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentDO record);

    int insertSelective(StudentDO record);

    List<StudentDO> selectByExample(StudentDOExample example);

    StudentDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentDO record, @Param("example") StudentDOExample example);

    int updateByExample(@Param("record") StudentDO record, @Param("example") StudentDOExample example);

    int updateByPrimaryKeySelective(StudentDO record);

    int updateByPrimaryKey(StudentDO record);
}