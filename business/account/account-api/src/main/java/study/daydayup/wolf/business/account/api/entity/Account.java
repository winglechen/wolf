package study.daydayup.wolf.business.account.api.entity;

import lombok.Data;

/**
 * study.daydayup.wolf.business.account.api.entity
 *
 * @author Wingle
 * @since 2019/9/27 5:12 PM
 **/
@Data
public class Account {
    private long accountId;
    private String account;
    private String name;
    private String avatar;
    private int gender;

}
