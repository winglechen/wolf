package study.daydayup.wolf.demo.my.plus.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * account
 * </p>
 *
 * @author winlechen
 * @since 2019-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("account")
@ApiModel(value="AccountDO对象", description="account")
public class AccountDO implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    @ApiModelProperty(value = "账号id")
    @TableId(value = "account_id", type = IdType.AUTO)
    private Long accountId;

    @ApiModelProperty(value = "手机号码")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    private String salt;

    @ApiModelProperty(value = "账号类型 0 默认、1 手机、2 email、3 alipay open_id 4 wechat open_id 5 wechat union_id")
    private Integer accountType;

    @ApiModelProperty(value = "账号来源 ")
    private Integer source;

    @ApiModelProperty(value = "账号角色 多角色用,分开 ")
    private String role;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "性别 0 默认、1 男、2 女")
    private Integer gender;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "是否删除 0未删除，1已删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "最后编辑者")
    private Long lastEditor;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "编辑时间")
    private Date updatedAt;


}
