package study.daydayup.wolf.business.trade.buy.service.impl;

import study.daydayup.wolf.business.trade.api.dto.buy.request.ConfirmRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PrepareRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PrepareResponse;
import study.daydayup.wolf.business.trade.api.service.buy.BuyService;

/**
 * study.daydayup.wolf.business.trade.buy.service.impl
 *
 * @author Wingle
 * @since 2019/10/9 1:59 下午
 **/
public class BuyServiceImpl implements BuyService {
    @Override
    public PrepareResponse prepare(PrepareRequest request) {
        PrepareResponse response = new PrepareResponse();

        return response;
    }

    @Override
    public ConfirmResponse confirm(ConfirmRequest request) {
        ConfirmResponse response = new ConfirmResponse();

        return response;
    }

    @Override
    public PayResponse pay(PayRequest request) {
        PayResponse response = new PayResponse();

        return response;
    }
}
