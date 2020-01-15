package study.daydayup.wolf.business.trade.buy.biz.epi;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.goods.api.dto.trade.TradeGoodsRequest;
import study.daydayup.wolf.business.goods.api.dto.trade.TradeGoodsResponse;
import study.daydayup.wolf.business.goods.api.service.TradeGoodsService;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.GoodsRequest;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Goods;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Installment;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Loan;
import study.daydayup.wolf.framework.layer.epi.Epi;

import java.util.ArrayList;
import java.util.List;


/**
 * study.daydayup.wolf.business.trade.buy.biz.epi
 *
 * @author Wingle
 * @since 2019/12/17 8:29 下午
 **/
@Component
public class GoodsEpi implements Epi {
    @Reference
    private TradeGoodsService goodsService;

    public List<Goods> fetch(List<GoodsRequest> goodsRequests) {
        if (goodsRequests == null || goodsRequests.isEmpty()) {
            return null;
        }

        List<TradeGoodsRequest> requests = formatRequest(goodsRequests);
        List<TradeGoodsResponse> responses = goodsService.find(requests);

        return formatResponse(responses, goodsRequests);
    }

    private List<Goods> formatResponse(List<TradeGoodsResponse> responses, List<GoodsRequest> goodsRequests) {
        if (responses == null || responses.isEmpty()) {
            return null;
        }

        List<Goods> goodsList = new ArrayList<>();
        for (TradeGoodsResponse response : responses) {
            Goods goods = formatTradeGoods(response);

            goods.setSku(null);
            goods.setLoan(formatTradeLoan(response));
            goods.setInstallmentList(formatTradeInstallment(response));

            goodsList.add(goods);
        }

        return goodsList;
    }

    private List<Installment> formatTradeInstallment(TradeGoodsResponse response) {
        List<Installment> installmentList = new ArrayList<>();

        List<study.daydayup.wolf.business.goods.api.vo.Installment> installments = response.getInstallmentList();
        for (int i = 0, len=installments.size(); i < len; i++) {
            study.daydayup.wolf.business.goods.api.vo.Installment installment = installments.get(i);

            Installment tradeInstallment = new Installment();

            BeanUtils.copyProperties(installment, tradeInstallment);
            tradeInstallment.setInstallmentNo(i+1);
            tradeInstallment.setInstallmentType(installment.getType());

            installmentList.add(tradeInstallment);
        }

        return installmentList;
    }

    private Loan formatTradeLoan(TradeGoodsResponse response) {
        Loan loan = new Loan();
        study.daydayup.wolf.business.goods.api.vo.Loan loanFromGoods = response.getLoan();
        BeanUtils.copyProperties(loanFromGoods, loan);

        loan.setInterestRate(loanFromGoods.getInterest());
        loan.setPenaltyRate(loanFromGoods.getPenalty());

        return loan;
    }

    private Goods formatTradeGoods(TradeGoodsResponse response) {
        Goods goods = Goods.builder()
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
