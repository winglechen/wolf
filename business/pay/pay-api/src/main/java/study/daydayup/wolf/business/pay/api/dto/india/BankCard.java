package study.daydayup.wolf.business.pay.api.dto.india;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.DTO;

/**
 * study.daydayup.wolf.business.pay.api.dto.india
 *
 * @author Wingle
 * @since 2020/3/22 10:11 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankCard implements DTO {
    private Long payerId;
    private Long payeeId;

    private String aadhaarNo;
    private String aadhaarName;
    private String email;
    private String mobile;

    private String bankName;
    private String accountIfsc;
    private String accountNumber;
}
