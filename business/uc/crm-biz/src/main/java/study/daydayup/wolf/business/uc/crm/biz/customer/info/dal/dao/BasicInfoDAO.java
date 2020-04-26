package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.BasicInfoDO;

public interface BasicInfoDAO {
    int deleteById(Long id);

    int insert(BasicInfoDO record);

    int insertSelective(BasicInfoDO record);

    BasicInfoDO selectById(Long id);

    int updateByIdSelective(BasicInfoDO record);

    int updateById(BasicInfoDO record);

    BasicInfoDO selectByAccountIdAndOrgId(@Param("accountId")Long accountId,@Param("orgId")Long orgId);



}