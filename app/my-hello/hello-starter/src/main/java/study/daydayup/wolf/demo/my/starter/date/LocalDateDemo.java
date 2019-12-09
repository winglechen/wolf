package study.daydayup.wolf.demo.my.starter.date;

import lombok.Data;
import study.daydayup.wolf.common.util.DateUtil;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * study.daydayup.wolf.demo.my.starter.date
 *
 * @author Wingle
 * @since 2019/12/9 5:01 下午
 **/
public class LocalDateDemo {
    public static void main(String[] args) {
        new LocalDateDemo().show();

    }

    public void show() {
        Account account = new Account();
        initDate(account);

        System.out.println(account);
    }

    public void initDate(Account account) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now:" + now);
        account.setCreatedAt(DateUtil.asDate(now));

        LocalDateTime next = now.plusSeconds(10*60);
        System.out.println("next:" + next);
        account.setExpiredAt(DateUtil.asDate(next));

    }

    @Data
    static class Account implements Serializable {
        private Date createdAt;
        private Date updatedAt;
        private Date ExpiredAt;
    }
}
