package study.daydayup.wolf.business.uc.setting.biz.dal.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.StaffStatusDO;

public interface StaffStatusDAO {
    int deleteById(Long id);

    int insert(StaffStatusDO record);

    int insertSelective(StaffStatusDO record);

    StaffStatusDO selectById(Long id);

    int updateByIdSelective(StaffStatusDO record);

    int updateById(StaffStatusDO record);

    int updateByAccountId(@Param("updated")StaffStatusDO updated,@Param("accountId")Long accountId,@Param("orgId")Long orgId);

    StaffStatusDO findByAccountId(@Param("accountId")Long accountId,@Param("orgId")Long orgId);


}