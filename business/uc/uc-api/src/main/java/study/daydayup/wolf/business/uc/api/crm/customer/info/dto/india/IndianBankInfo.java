package study.daydayup.wolf.business.uc.api.crm.customer.info.dto.india;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.DTO;

/**
 * study.daydayup.wolf.business.uc.api.crm.customer.info.dto.india
 *
 * @author Wingle
 * @since 2020/3/22 9:42 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndianBankInfo implements DTO {
    private Long accountId;
    private Long orgId;

    private String aadhaarNo;
    private String aadhaarName;
    private String email;

    private String bankName;
    private String accountIfsc;
    private String accountNumber;
}
