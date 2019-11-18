package study.daydayup.wolf.demo.account.biz.dal.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class WechatSessionKeyDO {

    private Long id;

    private Long uid;

    private String openId;

    private String unionId;

    private String sessionKey;

    private Integer isDelete;

    private Date createdAt;

    private Date updatedAt;

}