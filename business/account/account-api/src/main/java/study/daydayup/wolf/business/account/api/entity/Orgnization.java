package study.daydayup.wolf.business.account.api.entity;

import lombok.Data;

/**
 * study.daydayup.wolf.business.account.api.entity
 *
 * @author Wingle
 * @since 2019/9/27 5:36 PM
 **/
@Data
public class Orgnization {
    private long id;
    /**
     * @see study.daydayup.wolf.business.account.api.enums.OrgTypeEnum
     */
    private int orgType;
    private String orgName;
}
