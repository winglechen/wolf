package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.AddressBookDO;

public interface AddressBookDAO {
    int deleteById(Long id);

    int insert(AddressBookDO record);

    int insertSelective(AddressBookDO record);

    AddressBookDO selectById(Long id);

    int updateByIdSelective(AddressBookDO record);

    int updateById(AddressBookDO record);

    List<AddressBookDO> selectByAccountIdAndOrgId(@Param("accountId")Long accountId,@Param("orgId")Long orgId);


}