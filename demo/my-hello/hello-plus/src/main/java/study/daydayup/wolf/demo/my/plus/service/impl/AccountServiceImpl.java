package study.daydayup.wolf.demo.my.plus.service.impl;

import study.daydayup.wolf.demo.my.plus.dal.dataobject.AccountDO;
import study.daydayup.wolf.demo.my.plus.dal.dao.AccountDAO;
import study.daydayup.wolf.demo.my.plus.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * account 服务实现类
 * </p>
 *
 * @author winlechen
 * @since 2019-10-15
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountDAO, AccountDO> implements AccountService {

}
