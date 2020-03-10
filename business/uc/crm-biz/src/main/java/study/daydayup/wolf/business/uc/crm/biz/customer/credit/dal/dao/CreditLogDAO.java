package study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditLogDO;

public interface CreditLogDAO {
    int deleteById(Long id);

    int insert(CreditLogDO record);

    int insertSelective(CreditLogDO record);

    CreditLogDO selectById(Long id);

    int updateByIdSelective(CreditLogDO record);

    int updateById(CreditLogDO record);

    List<CreditLogDO> selectByAccountId(@Param("accountId")Long accountId);

    List<CreditLogDO> selectByOrgId(@Param("orgId")Long orgId);

    List<CreditLogDO> selectByOrgIdAndAccountId(@Param("orgId")Long orgId,@Param("accountId")Long accountId);



}