package study.daydayup.wolf.business.goods.biz.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class GoodsDO implements Serializable {
    private Long id;

    private Long orgId;

    private Integer categoryId;

    private Integer goodsType;

    private String name;

    private BigDecimal price;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    private Integer currency;

    private Integer chargeUnit;

    private Integer state;

    private Integer stockType;

    private Integer skuType;

    private String vsPrice;

    private String feature;

    private String mainPic;

    private String mainVideo;

    private String url;

    private String code;

    private String tags;

    private Long creator;

    private Integer version;

    private boolean deleteFlag;

    private Long lastEditor;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

}