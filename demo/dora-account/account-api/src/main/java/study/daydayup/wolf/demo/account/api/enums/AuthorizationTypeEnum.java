package study.daydayup.wolf.demo.account.api.enums;


import study.daydayup.wolf.demo.account.api.exception.AuthorizationTypeNonsupportException;

public enum AuthorizationTypeEnum {

    PASSWORD(1, "password", "密码授权"),

    ALIPAY(2, "alipay", "支付宝小程序"),
    ALIPAY_LIVE(3, "alipay", "支付宝生活号"),

    WECHAT_MP(4, "wechat", "微信公众号"),
    WECHAT_MINI(5, "wechat", "微信小程序"),
    WECHAT_APP(6, "wechat", "微信APP"),

    VERIFY_CODE(7, "verify_code", "验证码"),

    REFRESH_TOKEN(8, "refresh_token", "刷新令牌")
    ;

    private Integer type;

    private String name;

    private String desc;

    AuthorizationTypeEnum(Integer type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static AuthorizationTypeEnum getAuthorizationTypeEnumByType(Integer type) {
        if (type == null) {
            throw new AuthorizationTypeNonsupportException("授权类型不能为空");
        }
        for (AuthorizationTypeEnum authorizationTypeEnum : AuthorizationTypeEnum.values()) {
            if (authorizationTypeEnum.type.equals(type)) {
                return authorizationTypeEnum;
            }
        }
        throw new AuthorizationTypeNonsupportException("不支持该授权类型");
    }
}
