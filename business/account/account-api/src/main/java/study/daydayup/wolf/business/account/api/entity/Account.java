package study.daydayup.wolf.business.account.api.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Entity;

import javax.validation.constraints.NotBlank;

/**
 * study.daydayup.wolf.business.account.api.entity
 *
 * @author Wingle
 * @since 2019/9/27 5:12 PM
 **/
@Data
public class Account extends Entity {
    private long id;

    @NotBlank
    private String account;

    /**
     * @see study.daydayup.wolf.business.account.api.enums.AccountTypeEnum
     */
    private int accountType;

    private String password;
    private String salt;

    private String roles;
    private String source;


}
