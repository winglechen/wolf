package study.daydayup.wolf.demo.account.biz.authorization.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
public class DateExpiredVO {

    private Date expiredAt;

    private Long expiredIn;

    public DateExpiredVO(Date expiredAt) {
        this.expiredAt = expiredAt;
        initExpiredIn();
    }

    private void initExpiredIn() {
        expiredIn = (expiredAt.getTime() - (new Date()).getTime()) / 1000;
    }

    public boolean isExpired() {
        return  expiredAt.before(new Date());
    }

}
