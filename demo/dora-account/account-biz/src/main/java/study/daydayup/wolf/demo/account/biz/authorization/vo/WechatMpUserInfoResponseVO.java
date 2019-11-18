package study.daydayup.wolf.demo.account.biz.authorization.vo;

import study.daydayup.wolf.common.lang.enums.GenderEnum;
import lombok.Getter;

@Getter
public class WechatMpUserInfoResponseVO {

    private String openId;

    private String unionId;

    private String nickname;

    private Integer gender;

    private String avatar;

    private String country;

    private String province;

    private String city;

    /** 微信女性. **/
    private static final Integer WECHAT_FEMALE = 2;

    /** 微信男性. **/
    private static final Integer WECHAT_MALE = 1;

    public WechatMpUserInfoResponseVO(String openId, String unionId, String nickname, Integer gender, String avatar, String country, String province, String city) {
        this.openId = openId;
        this.unionId = unionId;
        this.nickname = nickname;
        setGender(gender);
        this.avatar = avatar;
        this.country = country;
        this.province = province;
        this.city = city;
    }

    private void setGender(Integer gender) {
        if (gender == null) {
            this.gender = GenderEnum.UNKNOWN.getCode();
            return;
        }
        if (gender.equals(WECHAT_FEMALE)) {
            this.gender = GenderEnum.FEMALE.getCode();
            return;
        }
        if (gender.equals(WECHAT_MALE)) {
            this.gender = GenderEnum.MALE.getCode();
            return;
        }

        this.gender = GenderEnum.UNKNOWN.getCode();
    }
}
