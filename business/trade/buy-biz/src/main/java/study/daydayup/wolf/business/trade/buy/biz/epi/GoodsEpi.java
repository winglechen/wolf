package study.daydayup.wolf.business.trade.buy.biz.epi;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.goods.api.dto.request.TradeGoodsRequest;
import study.daydayup.wolf.business.goods.api.dto.response.TradeGoodsResponse;
import study.daydayup.wolf.business.goods.api.service.TradeGoodsService;
import study.daydayup.wolf.business.goods.api.vo.Installment;
import study.daydayup.wolf.business.goods.api.vo.Loan;
import study.daydayup.wolf.business.trade.api.dto.buy.request.GoodsRequest;
import study.daydayup.wolf.business.trade.api.vo.buy.TradeGoods;
import study.daydayup.wolf.business.trade.api.vo.buy.TradeInstallment;
import study.daydayup.wolf.business.trade.api.vo.buy.TradeLoan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * study.daydayup.wolf.business.trade.buy.biz.epi
 *
 * @author Wingle
 * @since 2019/12/17 8:29 下午
 **/
@Component
public class GoodsEpi {
    @Reference
    private TradeGoodsService goodsService;

    public List<TradeGoods> fetch(List<GoodsRequest> goodsRequests) {
        if (goodsRequests == null || goodsRequests.isEmpty()) {
            return null;
        }

        List<TradeGoodsRequest> requests = formatRequest(goodsRequests);
        List<TradeGoodsResponse> responses = goodsService.find(requests);

        return formatResponse(responses, goodsRequests);
    }

    private List<TradeGoods> formatResponse(List<TradeGoodsResponse> responses, List<GoodsRequest> goodsRequests) {
        if (responses == null || responses.isEmpty()) {
            return null;
        }

        List<TradeGoods> goodsList = new ArrayList<>();
        for (TradeGoodsResponse response : responses) {
            TradeGoods goods = formatTradeGoods(response);

            goods.setSku(null);
            goods.setLoan(formatTradeLoan(response));
            goods.setInstallmentList(formatTradeInstallment(response));

            goodsList.add(goods);
        }

        return goodsList;
    }

    private List<TradeInstallment> formatTradeInstallment(TradeGoodsResponse response) {
        List<TradeInstallment> tradeInstallmentList = new ArrayList<>();

        List<Installment> installments = response.getInstallmentList();
        for (int i = 0, len=installments.size(); i < len; i++) {
            Installment installment = installments.get(i);

            TradeInstallment tradeInstallment = new TradeInstallment();

            BeanUtils.copyProperties(installment, tradeInstallment);
            tradeInstallment.setInstallmentNo(i+1);
            tradeInstallment.setInstallmentType(installment.getType());

            tradeInstallmentList.add(tradeInstallment);
        }

        return tradeInstallmentList;
    }

    private TradeLoan formatTradeLoan(TradeGoodsResponse response) {
        TradeLoan loan = new TradeLoan();
        BeanUtils.copyProperties(response.getLoan(), loan);

        return loan;
    }

    private TradeGoods formatTradeGoods(TradeGoodsResponse response) {
        TradeGoods goods = TradeGoods.builder()
                .sellId(response.getOrgId())
                .goodsId(response.getId())
                .categoryId(response.getCategoryId())
                .goodsName(response.getName())
                .goodsType(response.getGoodsType())
                .salePrice(response.getPrice())
                .currency(response.getCurrency())
                .chargeUnit(response.getChargeUnit())
                .goodsMainPic(response.getMainPic())
                .goodsVersion(response.getVersion())
                .goodsCode(response.getCode())
                .postage(response.getPostage())
                .build();

        return goods;
    }

    private List<TradeGoodsRequest> formatRequest(List<GoodsRequest> goodsRequests) {
        List<TradeGoodsRequest> requests = new ArrayList<>();

        for (GoodsRequest goodsRequest: goodsRequests) {
            TradeGoodsRequest request = TradeGoodsRequest.builder()
                    .orgId(goodsRequest.getOrgId())
                    .goodsId(goodsRequest.getGoodsId())
                    .skuId(goodsRequest.getSkuId())
                    .quantity(goodsRequest.getQuantity())
                    .build();

            requests.add(request);
        }

        return requests;
    }

    private void validResponse(List<TradeGoodsResponse> responses, List<GoodsRequest> requests ) {
        if (responses == null || responses.isEmpty()) {

        }
    }

}
