package study.daydayup.wolf.business.trade.buy.biz.epi;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.goods.api.dto.trade.TradeGoodsRequest;
import study.daydayup.wolf.business.goods.api.dto.trade.GoodsDTO;
import study.daydayup.wolf.business.goods.api.service.TradeGoodsService;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.LoanTerm;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.GoodsRequest;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Goods;
import study.daydayup.wolf.business.goods.api.vo.Installment;
import study.daydayup.wolf.business.goods.api.vo.Loan;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.epi.Epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
        List<GoodsDTO> responses = goodsService.find(requests).notNullData();

        return formatResponse(responses, goodsRequests);
    }

    private List<Goods> formatResponse(List<GoodsDTO> goodsDTOList, List<GoodsRequest> goodsRequests) {
        if (goodsDTOList == null || goodsDTOList.isEmpty()) {
            return null;
        }

        Map<Long, GoodsRequest> goodsRequestMap = CollectionUtil.map(goodsRequests, GoodsRequest::getGoodsId);

        List<Goods> goodsList = new ArrayList<>();
        for (GoodsDTO goodsDTO : goodsDTOList) {
            GoodsRequest goodsRequest = goodsRequestMap.get(goodsDTO.getId());
            Goods goods = formatTradeGoods(goodsDTO, goodsRequest);

            goods.setSku(null);
            goods.setLoanTerm(formatLoanTerm(goodsDTO));
            goods.setInstallmentTermList(formatInstallmentTerm(goodsDTO));

            goodsList.add(goods);
        }

        return goodsList;
    }

    private Goods formatTradeGoods(GoodsDTO goodsDTO, GoodsRequest goodsRequest) {
        Goods goods = Goods.builder()
                .sellId(goodsDTO.getOrgId())
                .goodsId(goodsDTO.getId())
                .categoryId(goodsDTO.getCategoryId())
                .goodsName(goodsDTO.getName())
                .goodsType(goodsDTO.getGoodsType())
                .salePrice(goodsDTO.getPrice())
                .currency(goodsDTO.getCurrency())
                .chargeUnit(goodsDTO.getChargeUnit())
                .goodsMainPic(goodsDTO.getMainPic())
                .goodsVersion(goodsDTO.getVersion())
                .goodsCode(goodsDTO.getCode())
                .postage(goodsDTO.getPostage())
                .build();

        if (goodsRequest != null) {
            goods.setGiftFlag(goodsRequest.getGiftFlag());
            goods.setPromotionId(goodsRequest.getPromotionId());
            goods.setQuantity(goodsRequest.getQuantity());
            goods.setMemo(goodsRequest.getMemo());
        }

        return goods;
    }

    private LoanTerm formatLoanTerm(GoodsDTO goodsDTO) {
        LoanTerm loanTerm = new LoanTerm();
        Loan loanFromGoods = goodsDTO.getLoan();
        BeanUtils.copyProperties(loanFromGoods, loanTerm);

        loanTerm.setSellerId(goodsDTO.getOrgId());
        loanTerm.setAmount(goodsDTO.getPrice());
        loanTerm.setCurrency(goodsDTO.getCurrency());

        loanTerm.setInterest(null);
        loanTerm.setPenalty(null);
        loanTerm.setInterestRate(loanFromGoods.getInterest());
        loanTerm.setPenaltyRate(loanFromGoods.getPenalty());

        return loanTerm;
    }

    private List<InstallmentTerm> formatInstallmentTerm(GoodsDTO goodsDTO) {
        List<InstallmentTerm> installmentList = new ArrayList<>();

        List<Installment> installmentDTOList = goodsDTO.getInstallmentList();
        for (int i = 0, len=installmentDTOList.size(); i < len; i++) {
            Installment installmentDTO = installmentDTOList.get(i);

            InstallmentTerm installmentTerm = new InstallmentTerm();
            BeanUtils.copyProperties(installmentDTO, installmentTerm);

            installmentTerm.setSellerId(goodsDTO.getOrgId());
            installmentTerm.setInstallmentNo(i+1);
            installmentTerm.setInstallmentType(installmentDTO.getType());

            installmentList.add(installmentTerm);
        }

        return installmentList;
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

}
