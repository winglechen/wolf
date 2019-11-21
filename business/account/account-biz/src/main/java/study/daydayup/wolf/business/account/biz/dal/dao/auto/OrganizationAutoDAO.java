package study.daydayup.wolf.business.account.biz.dal.dao.auto;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.account.biz.dal.dataobject.OrganizationDO;

@Mapper
public interface OrganizationAutoDAO {
    int insert(OrganizationDO record);

    int insertSelective(OrganizationDO record);

    OrganizationDO selectById(Long id);

    int updateByIdSelective(OrganizationDO record);

    int updateById(OrganizationDO record);
}