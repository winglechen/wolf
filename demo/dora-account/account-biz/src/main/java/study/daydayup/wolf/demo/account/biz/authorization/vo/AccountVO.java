package study.daydayup.wolf.demo.account.biz.authorization.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountVO {

    private Long uid;

    public Boolean isValid() {
        return uid != null && uid > 0;
    }
}
