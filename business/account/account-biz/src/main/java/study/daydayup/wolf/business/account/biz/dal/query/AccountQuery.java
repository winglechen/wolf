package study.daydayup.wolf.business.account.biz.dal.query;

import lombok.Data;
import study.daydayup.wolf.business.account.biz.dal.dataobject.AccountDO;
import study.daydayup.wolf.framework.layer.dal.Query;

/**
 * study.daydayup.wolf.business.account.biz.dal.query
 *
 * @author Wingle
 * @since 2019/12/16 9:30 上午
 **/
@Data
public class AccountQuery extends Query {
    private AccountDO model;
    private AccountDO changes;
    private AccountDO rules;
}
