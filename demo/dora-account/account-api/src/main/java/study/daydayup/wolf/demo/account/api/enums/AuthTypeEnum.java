package study.daydayup.wolf.demo.account.api.enums;


import study.daydayup.wolf.demo.account.api.exception.AuthorizationTypeNonsupportException;

public enum AuthTypeEnum {

    PASSWORD(1, "password", "密码授权"),

    ALIPAY(2, "alipay", "支付宝小程序"),
    ALIPAY_LIVE(3, "alipay", "支付宝生活号"),

    WECHAT_MP(4, "wechat", "微信公众号"),
    WECHAT_MINI(5, "wechat", "微信小程序"),
    WECHAT_APP(6, "wechat", "微信APP"),

    VERIFY_CODE(7, "verify_code", "验证码"),

    REFRESH_TOKEN(8, "refresh_token", "刷新令牌")
    ;

    private int code;
    private String name;
    private String desc;

    AuthTypeEnum(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static AuthTypeEnum getAuthorizationTypeEnumByType(int type) {
        for (AuthTypeEnum authTypeEnum : AuthTypeEnum.values()) {
            if (authTypeEnum.code == type) {
                return authTypeEnum;
            }
        }
        throw new AuthorizationTypeNonsupportException("不支持该授权类型");
    }
}
