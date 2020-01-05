package study.daydayup.wolf.business.goods.api.dto.request;

import lombok.Data;
import study.daydayup.wolf.business.goods.api.vo.Installment;
import study.daydayup.wolf.business.goods.api.vo.Loan;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.dto.request
 *
 * @author Wingle
 * @since 2019/12/11 12:00 下午
 **/
@Data
@Deprecated
public class GoodsCreateRequest implements Request {
    @Null
    private Long id;
    @Min(1)
    private Long orgId;
    @NotBlank
    private String name;
    @Min(1)
    private Long price;

    //goods defined in the config
    private Integer categoryId;
    private Integer goodsType;
    private byte state;

    private Integer currency;
    private Integer chargeUnit;

    private Integer stockType;
    private String vsPrice;
    private String feature;
    private String mainPic;
    private String mainVideo;
    private String code;
    private String tags;

    private Long creator;

    private Loan loan;
    private List<Installment> installments;
}
