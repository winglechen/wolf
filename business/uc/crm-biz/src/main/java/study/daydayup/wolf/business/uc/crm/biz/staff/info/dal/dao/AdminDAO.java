package study.daydayup.wolf.business.uc.crm.biz.staff.info.dal.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.business.uc.crm.biz.staff.info.dal.dataobject.AdminDO;

public interface AdminDAO {
    int deleteById(Long id);

    int insert(AdminDO record);

    int insertSelective(AdminDO record);

    AdminDO selectById(Long id);

    int updateByIdSelective(AdminDO record);

    int updateById(AdminDO record);

    List<AdminDO> selectByAccountId(@Param("accountId")Long accountId);



}