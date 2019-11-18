package study.daydayup.wolf.demo.account.api.enums;

public enum GenderEnum {
    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女");

    private Integer code;

    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    GenderEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
