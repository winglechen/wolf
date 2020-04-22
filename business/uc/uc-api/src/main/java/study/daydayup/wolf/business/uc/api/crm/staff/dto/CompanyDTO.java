package study.daydayup.wolf.business.uc.api.crm.staff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.DTO;

/**
 * study.daydayup.wolf.business.union.admin.dto
 *
 * @author Wingle
 * @since 2020/4/22 9:55 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO implements DTO {
    private Long orgId;
    private String name;
    private String logo;
}
