package study.daydayup.wolf.business.trade.order.biz.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OrderLineDO implements Serializable {
    private Long id;

    private String tradeNo;

    private Long buyerId;

    private Long sellerId;

    private Long goodsId;

    private Long skuId;

    private Integer categoryId;

    private Integer goodsType;

    private Integer goodsVersion;

    private String sku;

    private String goodsName;

    private String goodsMainPic;

    private Long salePrice;

    private Long payPrice;

    private Long postage;

    private Integer currency;

    private Integer chargeUnit;

    private Integer quantity;

    private String buyerMemo;

    private boolean giftFlag;

    private Integer consignState;

    private Integer disputeState;

    private LocalDateTime consignedAt;

    private LocalDateTime disputeAt;

    private Integer version;

    private boolean deleteFlag;

    private Long lastEditor;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", skuId=").append(skuId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", goodsType=").append(goodsType);
        sb.append(", goodsVersion=").append(goodsVersion);
        sb.append(", sku=").append(sku);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsMainPic=").append(goodsMainPic);
        sb.append(", salePrice=").append(salePrice);
        sb.append(", payPrice=").append(payPrice);
        sb.append(", postage=").append(postage);
        sb.append(", currency=").append(currency);
        sb.append(", chargeUnit=").append(chargeUnit);
        sb.append(", quantity=").append(quantity);
        sb.append(", buyerMemo=").append(buyerMemo);
        sb.append(", giftFlag=").append(giftFlag);
        sb.append(", consignState=").append(consignState);
        sb.append(", disputeState=").append(disputeState);
        sb.append(", consignedAt=").append(consignedAt);
        sb.append(", disputeAt=").append(disputeAt);
        sb.append(", version=").append(version);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", lastEditor=").append(lastEditor);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrderLineDO other = (OrderLineDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getBuyerId() == null ? other.getBuyerId() == null : this.getBuyerId().equals(other.getBuyerId()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getGoodsType() == null ? other.getGoodsType() == null : this.getGoodsType().equals(other.getGoodsType()))
            && (this.getGoodsVersion() == null ? other.getGoodsVersion() == null : this.getGoodsVersion().equals(other.getGoodsVersion()))
            && (this.getSku() == null ? other.getSku() == null : this.getSku().equals(other.getSku()))
            && (this.getGoodsName() == null ? other.getGoodsName() == null : this.getGoodsName().equals(other.getGoodsName()))
            && (this.getGoodsMainPic() == null ? other.getGoodsMainPic() == null : this.getGoodsMainPic().equals(other.getGoodsMainPic()))
            && (this.getSalePrice() == null ? other.getSalePrice() == null : this.getSalePrice().equals(other.getSalePrice()))
            && (this.getPayPrice() == null ? other.getPayPrice() == null : this.getPayPrice().equals(other.getPayPrice()))
            && (this.getPostage() == null ? other.getPostage() == null : this.getPostage().equals(other.getPostage()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getChargeUnit() == null ? other.getChargeUnit() == null : this.getChargeUnit().equals(other.getChargeUnit()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
            && (this.getBuyerMemo() == null ? other.getBuyerMemo() == null : this.getBuyerMemo().equals(other.getBuyerMemo()))
            && (this.isGiftFlag() == other.isGiftFlag())
            && (this.getConsignState() == null ? other.getConsignState() == null : this.getConsignState().equals(other.getConsignState()))
            && (this.getDisputeState() == null ? other.getDisputeState() == null : this.getDisputeState().equals(other.getDisputeState()))
            && (this.getConsignedAt() == null ? other.getConsignedAt() == null : this.getConsignedAt().equals(other.getConsignedAt()))
            && (this.getDisputeAt() == null ? other.getDisputeAt() == null : this.getDisputeAt().equals(other.getDisputeAt()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.isDeleteFlag() == other.isDeleteFlag())
            && (this.getLastEditor() == null ? other.getLastEditor() == null : this.getLastEditor().equals(other.getLastEditor()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getBuyerId() == null) ? 0 : getBuyerId().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getGoodsType() == null) ? 0 : getGoodsType().hashCode());
        result = prime * result + ((getGoodsVersion() == null) ? 0 : getGoodsVersion().hashCode());
        result = prime * result + ((getSku() == null) ? 0 : getSku().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getGoodsMainPic() == null) ? 0 : getGoodsMainPic().hashCode());
        result = prime * result + ((getSalePrice() == null) ? 0 : getSalePrice().hashCode());
        result = prime * result + ((getPayPrice() == null) ? 0 : getPayPrice().hashCode());
        result = prime * result + ((getPostage() == null) ? 0 : getPostage().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getChargeUnit() == null) ? 0 : getChargeUnit().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getBuyerMemo() == null) ? 0 : getBuyerMemo().hashCode());
        result = prime * result + (isGiftFlag() ? 1231 : 1237);
        result = prime * result + ((getConsignState() == null) ? 0 : getConsignState().hashCode());
        result = prime * result + ((getDisputeState() == null) ? 0 : getDisputeState().hashCode());
        result = prime * result + ((getConsignedAt() == null) ? 0 : getConsignedAt().hashCode());
        result = prime * result + ((getDisputeAt() == null) ? 0 : getDisputeAt().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + (isDeleteFlag() ? 1231 : 1237);
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}