package study.daydayup.wolf.demo.account.api.enums;

public enum AccountSourceEnum {
    UNKNOWN(0, "未知"),
    QIE_H5(1, "h5"),
    ALIPAY(2, "支付宝"),
    WECHAT(3, "微信");

    private Integer source;

    private String desc;

    public Integer getSource() {
        return source;
    }

    public String getDesc() {
        return desc;
    }

    AccountSourceEnum(Integer source, String desc) {
        this.source = source;
        this.desc = desc;
    }
}
