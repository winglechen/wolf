package study.daydayup.wolf.business.trade.api.dto.buy.request;

import lombok.Data;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy
 *
 * @author Wingle
 * @since 2019/10/5 11:26 AM
 **/
@Data
public class ConfirmRequest extends PreviewRequest {
    private String TradeNo;
    private UmpRequest ump;
}
