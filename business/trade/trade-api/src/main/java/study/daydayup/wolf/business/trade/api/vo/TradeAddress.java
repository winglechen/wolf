package study.daydayup.wolf.business.trade.api.vo;

import lombok.Data;
import study.daydayup.wolf.framework.layer.domain.VO;

/**
 * study.daydayup.wolf.business.trade.api.entity
 *
 * @author Wingle
 * @since 2019/10/5 1:26 PM
 **/
@Data
public class TradeAddress extends VO {
    /**
     * @see
     */
    private int addressType;

    private int areaCode;
    private String province;
    private String city;
    private String county;
    private String address;

    private String consignee;
    private String contactInfo;
}
