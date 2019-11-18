package study.daydayup.wolf.demo.account.api.enums;

public enum AccountTypeEnum {
    UNKNOWN(0, "未知"),
    MOBILE(1, "手机号码"),
    EMAIL(2, "email"),
    ALIPAY_OPEN_ID(3, "alipay open_id"),
    WECHAT_OPEN_ID(4, "wechat open_id"),
    WECHAT_UNION_ID(5, "wechat union_id")

    ;

    private Integer type;

    private String desc;

    AccountTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
