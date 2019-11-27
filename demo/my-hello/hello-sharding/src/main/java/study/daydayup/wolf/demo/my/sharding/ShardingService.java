package study.daydayup.wolf.demo.my.sharding;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.demo.my.sharding.dal.AccountDAO;
import study.daydayup.wolf.demo.my.sharding.dal.AccountDO;
import study.daydayup.wolf.demo.my.sharding.dal.TagDAO;
import study.daydayup.wolf.demo.my.sharding.dal.TagDO;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/tags")
    public String tags() {
        List<Map> list = tagDAO.selectAll();

//        Map<String, Object> map = list.get(0);
//        System.out.println("row[0]: " + map);
//        System.out.println("map.class: " + map.getClass());
//
//        String id = (String) map.get("id");


        for(Map item : list) {
//            System.out.println(item);
//            System.out.println("keys:" + item.keySet());
//            System.out.println("vals:" + item.values());

            if(item.get("id").equals(Long.valueOf(283))) {
                System.out.println("<Long>283.tags= " + item.get("tags"));
            }

            if(item.get("id").equals(283)) {
                System.out.println("<283>283.tags= " + item.get("tags"));
            }

            if(283 == (long)item.get("id")) {
                System.out.println("<scalar.long>283.tags= " + item.get("tags"));
            }

            if(item.get("id").toString().equals("283")) {
                System.out.println("<String>283.tags= " + item.get("tags"));
            }

            if(item.get("tags").equals("abc")) {
                System.out.println("abc.id= " + item.get("id"));
            }
        }

       return "tags";
    }

//    public static void main(String[] args) {
//    }
}
