package study.daydayup.wolf.demo.account.biz.dal.dataobject;

import lombok.Data;

import java.util.Date;
@Data
public class VerifyCodeDO {

    private Long id;

    private String mobile;

    private String code;

    private Date expiredAt;

    private Integer isDelete;

    private Date createdAt;

    private Date updatedAt;

}