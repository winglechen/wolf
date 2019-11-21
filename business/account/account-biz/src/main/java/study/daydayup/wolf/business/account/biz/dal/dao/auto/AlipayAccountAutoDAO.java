package study.daydayup.wolf.business.account.biz.dal.dao.auto;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.account.biz.dal.dataobject.AlipayAccountDO;

@Mapper
public interface AlipayAccountAutoDAO {
    int insert(AlipayAccountDO record);

    int insertSelective(AlipayAccountDO record);

    AlipayAccountDO selectById(Long id);

    int updateByIdSelective(AlipayAccountDO record);

    int updateById(AlipayAccountDO record);
}