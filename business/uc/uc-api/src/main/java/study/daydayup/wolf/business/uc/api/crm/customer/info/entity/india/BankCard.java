package study.daydayup.wolf.business.uc.api.crm.customer.info.entity.india;

import lombok.Data;

import study.daydayup.wolf.framework.layer.api.Model;
import java.time.LocalDateTime;

@Data
public class BankCard implements Model {
    private Long accountId;
    private Long orgId;

    private String bankName;
    private String branch;
    private String ifsc;

    private String address;
    private String bankNo;
    private String relegationBank;

    private Integer type;
    private Boolean isLoan;
    private Boolean isPay;

    private LocalDateTime expirationDate;

    private String frontSide;
    private String backSide;

    private Boolean deleteFlag;
    private Integer version;

    private Long lastEditor;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final long serialVersionUID = 1L;

}