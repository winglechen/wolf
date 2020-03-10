package study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao;
import org.apache.ibatis.annotations.Param;

import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditConfigDO;

public interface CreditConfigDAO {
    int deleteById(Long id);

    int insert(CreditConfigDO record);

    int insertSelective(CreditConfigDO record);

    CreditConfigDO selectById(Long id);

    int updateByIdSelective(CreditConfigDO record);

    int updateById(CreditConfigDO record);

    CreditConfigDO selectByOrgId(@Param("orgId")Long orgId);

    int updateByOrgId(@Param("updated")CreditConfigDO updated, @Param("orgId")Long orgId);

}