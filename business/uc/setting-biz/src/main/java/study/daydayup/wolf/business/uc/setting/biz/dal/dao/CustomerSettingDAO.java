package study.daydayup.wolf.business.uc.setting.biz.dal.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.CustomerSettingDO;

public interface CustomerSettingDAO {
    int deleteById(Long id);

    int insert(CustomerSettingDO record);

    int insertSelective(CustomerSettingDO record);

    CustomerSettingDO selectById(Long id);

    int updateByIdSelective(CustomerSettingDO record);

    int updateByPrimaryKeyWithBLOBs(CustomerSettingDO record);

    int updateById(CustomerSettingDO record);

    int updateByAccountId(@Param("updated")CustomerSettingDO updated,@Param("accountId")Long accountId,@Param("orgId")Long orgId);

    CustomerSettingDO findByAccountId(@Param("accountId")Long accountId,@Param("orgId")Long orgId);


}