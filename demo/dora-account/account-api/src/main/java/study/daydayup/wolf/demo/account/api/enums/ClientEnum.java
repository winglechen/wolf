package study.daydayup.wolf.demo.account.api.enums;

import study.daydayup.wolf.demo.account.api.exception.ClientNonsupportException;

public enum ClientEnum {

    XBXC_WECHAT_APP(1, "weapp_wtzz_v1", "小杯相册app微信登录"),
    XBXC_WECHAT_MP(2, "wemp_wtxc_v1", "小杯相册微信公众号登录"),
    XBXC_WECHAT_MINI(3, "wmp_wtxc_v1", "小杯相册微信小程序登录")
    ;

    private Integer type;

    private String id;

    private String desc;

    ClientEnum(Integer type, String id, String desc) {
        this.type = type;
        this.id = id;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static ClientEnum getClientEnumByType(Integer type) {
        if (type == null) {
            throw new ClientNonsupportException("登录客户端类型不能为空");
        }
        for (ClientEnum clientEnum : ClientEnum.values()) {
            if (clientEnum.type.equals(type)) {
                return clientEnum;
            }
        }
        throw new ClientNonsupportException("不支持该登录客户端类型");
    }

    public static ClientEnum getClientEnumById(String id) {
        if (id == null) {
            throw new ClientNonsupportException("登录客户端类型不能为空");
        }
        for (ClientEnum clientEnum : ClientEnum.values()) {
            if (clientEnum.id.equals(id)) {
                return clientEnum;
            }
        }
        throw new ClientNonsupportException("不支持该登录客户端类型");
    }
}
