package study.daydayup.wolf.demo.account.biz.authorization.entity;

import study.daydayup.wolf.demo.account.biz.authorization.vo.DateExpiredVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.Random;

@Setter
@Getter
@NoArgsConstructor
public class VerifyCode {

    private Long id;

    private String mobile;

    private String code;

    private DateExpiredVO expiredVO;

    private Date createAt;

    public VerifyCode(String mobile) {
        this.mobile = mobile;
        generateCode();
        generateExpired();
        createAt = new Date();
    }

    private void generateCode() {
        Random random = new Random();
        Integer integerCode = random.nextInt(9000) + 1000;
        code = integerCode.toString();
    }

    private void generateExpired() {
        expiredVO = new DateExpiredVO(DateUtils.addSeconds(new Date(), 60));
    }

    public boolean isExpired() {
        return expiredVO.isExpired();
    }

}
