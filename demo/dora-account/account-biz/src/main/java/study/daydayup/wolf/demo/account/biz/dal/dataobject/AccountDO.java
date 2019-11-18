package study.daydayup.wolf.demo.account.biz.dal.dataobject;

import lombok.Data;

import java.util.Date;

/**
* Created by Mybatis Generator 2019/09/26
*/
@Data
public class AccountDO {
    private Long id;

    /**
	* 账号 手机、邮箱
	*/
    private String account;

    /**
	* 账号类型 0 默认、1 手机、2 email、3 alipay open_id、4 wechat open_id、5 wechat union_id
	*/
    private Integer type;

    /**
     * 账号来源 0 默认、2 alipay、3 wechat
     */
    private Integer source;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别 0 默认、1 男、2 女
     */
    private Integer gender;

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