package study.daydayup.wolf.demo.account.biz.dal.dao;

import study.daydayup.wolf.demo.account.biz.dal.dataobject.ClientInfoDO;
import org.apache.ibatis.annotations.Param;

public interface ClientInfoDAO {

    int insert(ClientInfoDO record);

    ClientInfoDO getById(Integer id);

    ClientInfoDO getByClientId(@Param("clientId") String clientId);


}