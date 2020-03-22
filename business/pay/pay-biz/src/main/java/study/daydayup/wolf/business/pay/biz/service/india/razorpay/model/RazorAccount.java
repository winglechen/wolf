package study.daydayup.wolf.business.pay.biz.service.india.razorpay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.Model;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay
 *
 * @author Wingle
 * @since 2020/3/21 6:22 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RazorAccount implements Model {
    private Long id;

    private Long payerId;
    private String payerName;
    private Long payeeId;
    private String payeeName;

    private Integer state;

    private String contactId;
    private Boolean contactActive;

    private String contact;
    private String email;
    private String contactType;

    private String accountId;
    private String accountType;
    private Boolean accountActive;

    private String accountName;
    private String bankName;
    private String accountIfsc;
    private String accountNumber;

    private String vpaAddress;
    private String cardName;
    private String cardLast4;
    private String cardNetwork;
    private String cardType;
    private String cardIssuer;

    private String tags;
    private Boolean deleteFlag;
    private LocalDateTime createdAt;
}
