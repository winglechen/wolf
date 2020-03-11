package study.daydayup.wolf.business.uc.api.acl.entity;

import study.daydayup.wolf.business.uc.api.acl.enums.ActionTypeEnum;
import study.daydayup.wolf.framework.layer.api.Model;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.api.acl.entity
 *
 * @author Wingle
 * @since 2020/1/9 12:42 下午
 **/
public class Action implements Model {
    /**
     * @see ActionTypeEnum
     */
    private Integer actionType;

    private Integer id;
    private String key;
    private String name;
    private String uri;

    private List<String> children;
}
