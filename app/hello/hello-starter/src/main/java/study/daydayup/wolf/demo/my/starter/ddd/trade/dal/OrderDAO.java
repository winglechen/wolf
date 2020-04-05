package study.daydayup.wolf.demo.my.starter.ddd.trade.dal;

/**
 * study.daydayup.wolf.demo.my.starter.ddd.trade.dal
 *
 * @author Wingle
 * @since 2019/12/25 4:32 下午
 **/
public class OrderDAO {
    public String insert(OrderDO orderDO) {
        System.out.println("insert order successfully");
        return "insert order successfully";
    }

    public void update(OrderDO locker, OrderDO changes) {
        System.out.println("update order successfully");
    }
}
