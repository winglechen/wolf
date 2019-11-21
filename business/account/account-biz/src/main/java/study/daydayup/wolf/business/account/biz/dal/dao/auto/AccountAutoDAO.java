package study.daydayup.wolf.business.account.biz.dal.dao.auto;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.account.biz.dal.dataobject.AccountDO;

@Mapper
public interface AccountAutoDAO {
    int insert(AccountDO record);

    int insertSelective(AccountDO record);

    AccountDO selectById(Long id);

    int updateByIdSelective(AccountDO record);

    int updateById(AccountDO record);
}