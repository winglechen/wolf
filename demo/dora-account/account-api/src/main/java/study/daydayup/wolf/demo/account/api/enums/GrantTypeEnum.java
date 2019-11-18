package study.daydayup.wolf.demo.account.api.enums;


import study.daydayup.wolf.demo.account.api.exception.GrantTypeNonsupportException;

public enum GrantTypeEnum {
    THIRD_AUTH_INFO(1, "third_auth_info", "第三方登录信息"),
    TOKEN(2, "token", "token令牌"),
    OAUTH2(3, "oauth2", "oauth2.0 access_token+refresh_token"),
    JWT(4, "jwt", "jwt");


    private Integer type;

    private String name;

    private String desc;

    GrantTypeEnum(Integer type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }


    public static GrantTypeEnum geGrantTypeEnumByType(Integer type) {
        if (type == null) {
            throw new GrantTypeNonsupportException("授予证书类型不能为空");
        }
        for (GrantTypeEnum grantTypeEnum : GrantTypeEnum.values()) {
            if (grantTypeEnum.type.equals(type)) {
                return grantTypeEnum;
            }
        }
        throw new GrantTypeNonsupportException("不支持该授予证书类型");
    }
}
