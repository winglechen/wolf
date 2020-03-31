package study.daydayup.wolf.business.uc.setting.biz.dal.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.CompanyStatusDO;

public interface CompanyStatusDAO {
    int deleteById(Long id);

    int insert(CompanyStatusDO record);

    int insertSelective(CompanyStatusDO record);

    CompanyStatusDO selectById(Long id);

    int updateByIdSelective(CompanyStatusDO record);

    int updateById(CompanyStatusDO record);

    CompanyStatusDO findByOrgId(@Param("orgId")Long orgId);

    int updateByOrgId(@Param("updated")CompanyStatusDO updated,@Param("orgId")Long orgId);



}