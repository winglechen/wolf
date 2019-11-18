package study.daydayup.wolf.demo.account.biz.dal.dataobject;

import lombok.Data;

import java.util.Date;

/**
* Created by Mybatis Generator 2019/09/22
*/
@Data
public class AccountWechatDO {

    private Long id;

    /**
	* 账号id
	*/
    private Long uid;

    /**
	* 微信 open id
	*/
    private String openId;

    /**
     * 微信小程序 open id
     */
    private String mpOpenId;

    /**
	* 微信 union id
	*/
    private String unionId;

    /**
	* 版本号
	*/
    private Integer version;

    /**
	* 是否删除 0未删除，1已删除
	*/
    private Integer deleteFlag;

    /**
	* 最后编辑者
	*/
    private Long lastEditor;

    /**
	* 创建时间
	*/
    private Date createTime;

    /**
	* 编辑时间
	*/
    private Date updateTime;
}