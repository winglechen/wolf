package study.daydayup.wolf.demo.account.api.enums;

public enum AccountTypeEnum {
    UNKNOWN(0, "未知"),
    MOBILE(1, "手机号码"),
    EMAIL(2, "email"),
    ALIPAY_OPEN_ID(3, "alipay open_id"),
    WECHAT_OPEN_ID(4, "wechat open_id"),
    WECHAT_UNION_ID(5, "wechat union_id")
    ;

    private int code;
    private String desc;

    AccountTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
