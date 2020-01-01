package study.daydayup.wolf.business.uc.setting.biz.dal.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.CustomerStatusDO;

public interface CustomerStatusDAO {
    int deleteById(Long id);

    int insert(CustomerStatusDO record);

    int insertSelective(CustomerStatusDO record);

    CustomerStatusDO selectById(Long id);

    int updateByIdSelective(CustomerStatusDO record);

    int updateById(CustomerStatusDO record);

    CustomerStatusDO findByAccountId(@Param("accountId")Long accountId, @Param("orgId")Long orgId);

    int updateByAccountId(@Param("updated")CustomerStatusDO updated,@Param("accountId")Long accountId, @Param("orgId")Long orgId);



}