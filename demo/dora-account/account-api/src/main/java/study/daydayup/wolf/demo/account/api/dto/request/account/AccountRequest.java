package study.daydayup.wolf.demo.account.api.dto.request.account;

import lombok.Data;
import study.daydayup.wolf.demo.account.api.enums.AccountSourceEnum;
import study.daydayup.wolf.demo.account.api.enums.AccountTypeEnum;
import study.daydayup.wolf.demo.account.api.enums.GenderEnum;

@Data
public class AccountRequest {

    private String account;

    private String password;

    /**
     * @see AccountTypeEnum
     */
    private Integer type;

    /**
     * @see AccountSourceEnum
     * 
     */
    private Integer source;


    private String nickname;

    /**
     * @see GenderEnum
     */
    private Integer gender;

    private String avatar;
}
