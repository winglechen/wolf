package study.daydayup.wolf.business.uc.api.setting.enums.customer;

import lombok.Getter;
import study.daydayup.wolf.business.uc.api.setting.enums.StatusEnum;

/**
 * study.daydayup.wolf.business.uc.api.setting.enums.customer
 *
 * @author Wingle
 * @since 2020/2/20 2:51 下午
 **/
@Getter
public enum CustomerStatusProgressEnum {
//    DIVING_LICENSE(CustomerInfoEnum.DIVING_LICENSE, CustomerInfoEnum.KYC),
//    VOTER(CustomerInfoEnum.VOTER, CustomerInfoEnum.KYC),
//    PAN(CustomerInfoEnum.PAN, CustomerInfoEnum.KYC),
    AADHAAR(CustomerInfoEnum.AADHAAR, CustomerInfoEnum.KYC),

    KYC(CustomerInfoEnum.KYC, CustomerInfoEnum.INFO_COMPLETE),
    BANK_CARD(CustomerInfoEnum.BANK_CARD, CustomerInfoEnum.INFO_COMPLETE),
    LIVENESS(CustomerInfoEnum.LIVENESS, CustomerInfoEnum.INFO_COMPLETE),
    BASIC_INFO(CustomerInfoEnum.BASIC_INFO, CustomerInfoEnum.INFO_COMPLETE)
            ;
    private StatusEnum status;
    private StatusEnum progress;

    CustomerStatusProgressEnum(StatusEnum status, StatusEnum progress) {
        this.status = status;
        this.progress = progress;
    }
}
