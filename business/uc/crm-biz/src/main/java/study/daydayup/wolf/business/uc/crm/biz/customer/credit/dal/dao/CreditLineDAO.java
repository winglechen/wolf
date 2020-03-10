package study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao;
import java.util.Collection;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditLineDO;

public interface CreditLineDAO {
    int deleteById(Long id);

    int insert(CreditLineDO record);

    int insertSelective(CreditLineDO record);

    CreditLineDO selectById(Long id);

    int updateByIdSelective(CreditLineDO record);

    int updateById(CreditLineDO record);

    List<CreditLineDO> selectByAccountId(@Param("accountId")Long accountId);

    List<CreditLineDO> selectByOrgId(@Param("orgId")Long orgId);

    List<CreditLineDO> selectByAccountIdIn(@Param("accountIdCollection")Collection<Long> accountIdCollection);

    CreditLineDO selectByAccountIdAndOrgId(@Param("accountId")Long accountId,@Param("orgId")Long orgId);

    int updateByAccountIdAndOrgId(@Param("updated")CreditLineDO updated,@Param("accountId")Long accountId,@Param("orgId")Long orgId);


}