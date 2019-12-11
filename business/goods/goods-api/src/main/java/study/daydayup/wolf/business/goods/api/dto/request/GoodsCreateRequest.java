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
public class GoodsCreateRequest extends Request {
    @Null
    private long id;
    @Min(1)
    private long orgId;
    @NotBlank
    private String name;
    @Min(1)
    private long price;

    //goods defined in the config
    private int categoryId;
    private int goodsType;
    private byte state;

    private int currency;
    private int chargeUnit;

    private int stockType;
    private String vsPrice;
    private String feature;
    private String mainPic;
    private String mainVideo;
    private String code;
    private String tags;

    private long creator;

    private Loan loan;
    private List<Installment> installments;
}
