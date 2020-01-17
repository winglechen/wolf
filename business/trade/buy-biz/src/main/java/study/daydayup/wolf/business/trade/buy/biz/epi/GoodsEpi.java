package study.daydayup.wolf.business.trade.buy.biz.epi;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.goods.api.dto.trade.TradeGoodsRequest;
import study.daydayup.wolf.business.goods.api.dto.trade.TradeGoodsDTO;
import study.daydayup.wolf.business.goods.api.service.TradeGoodsService;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.LoanTerm;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.GoodsRequest;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Goods;
import study.daydayup.wolf.business.goods.api.vo.Installment;
import study.daydayup.wolf.business.goods.api.vo.Loan;
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
        List<TradeGoodsDTO> responses = goodsService.find(requests).notNullData();

        return formatResponse(responses);
    }

    private List<Goods> formatResponse(List<TradeGoodsDTO> goodsDTOList) {
        if (goodsDTOList == null || goodsDTOList.isEmpty()) {
            return null;
        }

        List<Goods> goodsList = new ArrayList<>();
        for (TradeGoodsDTO goodsDTO : goodsDTOList) {
            Goods goods = formatTradeGoods(goodsDTO);

            goods.setSku(null);
            goods.setLoanTerm(formatTradeLoan(goodsDTO));
            goods.setInstallmentTermList(formatTradeInstallment(goodsDTO));

            goodsList.add(goods);
        }

        return goodsList;
    }

    private List<InstallmentTerm> formatTradeInstallment(TradeGoodsDTO goodsDTO) {
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

    private LoanTerm formatTradeLoan(TradeGoodsDTO goodsDTO) {
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

    private Goods formatTradeGoods(TradeGoodsDTO response) {
        return Goods.builder()
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
