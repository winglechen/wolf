package study.daydayup.wolf.demo.my.sharding;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.demo.my.sharding.dal.AccountDAO;
import study.daydayup.wolf.demo.my.sharding.dal.AccountDO;
import study.daydayup.wolf.demo.my.sharding.dal.TagDAO;
import study.daydayup.wolf.demo.my.sharding.dal.TagDO;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.demo.my.sharding
 *
 * @author Wingle
 * @since 2019/11/13 8:18 下午
 **/
@RestController
public class ShardingService {
    @Resource
    private TagDAO tagDAO;
    @Resource
    private AccountDAO accountDAO;

    @RequestMapping("/tag")
    public String tag() {
//        System.out.println("hello tag");
//        return "hello tag";
        TagDO tag = tagDAO.getById(1);
        System.out.println("tag: " + tag);
        return tag.toString();
    }

    @RequestMapping("/account")
    public String account() {
        AccountDO account = accountDAO.getById(1);
        System.out.println("account: " + account );
        return account.toString();
    }
}
