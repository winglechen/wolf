package study.daydayup.wolf.demo.account.api.enums;

public enum AccountSourceEnum {
    UNKNOWN(0, "未知"),
    QIE_H5(1, "h5"),
    ALIPAY(2, "支付宝"),
    WECHAT(3, "微信");

    private int code;

    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    AccountSourceEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
