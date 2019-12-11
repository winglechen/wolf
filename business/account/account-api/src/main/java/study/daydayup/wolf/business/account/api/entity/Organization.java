package study.daydayup.wolf.business.account.api.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.domain.Entity;

/**
 * study.daydayup.wolf.business.account.api.entity
 *
 * @author Wingle
 * @since 2019/9/27 5:36 PM
 **/
@Data
public class Organization extends Entity {
    private long id;
    /**
     * @see study.daydayup.wolf.business.account.api.enums.OrgTypeEnum
     */
    private int orgType;
    private String orgName;
}
