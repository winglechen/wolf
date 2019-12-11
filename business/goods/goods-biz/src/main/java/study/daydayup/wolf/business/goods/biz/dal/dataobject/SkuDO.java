package study.daydayup.wolf.business.goods.biz.dal.dataobject;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SkuDO implements Serializable {
    private Long id;

    private Long goodsId;

    private Long orgId;

    private Long price;

    private Integer currency;

    private Integer chargeUnit;

    private Integer stockType;

    private String sku;

    private String skuKey;

    private Integer version;

    private boolean deleteFlag;

    private Long lastEditor;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", orgId=").append(orgId);
        sb.append(", price=").append(price);
        sb.append(", currency=").append(currency);
        sb.append(", chargeUnit=").append(chargeUnit);
        sb.append(", stockType=").append(stockType);
        sb.append(", sku=").append(sku);
        sb.append(", skuKey=").append(skuKey);
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
        SkuDO other = (SkuDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getChargeUnit() == null ? other.getChargeUnit() == null : this.getChargeUnit().equals(other.getChargeUnit()))
            && (this.getStockType() == null ? other.getStockType() == null : this.getStockType().equals(other.getStockType()))
            && (this.getSku() == null ? other.getSku() == null : this.getSku().equals(other.getSku()))
            && (this.getSkuKey() == null ? other.getSkuKey() == null : this.getSkuKey().equals(other.getSkuKey()))
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
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getChargeUnit() == null) ? 0 : getChargeUnit().hashCode());
        result = prime * result + ((getStockType() == null) ? 0 : getStockType().hashCode());
        result = prime * result + ((getSku() == null) ? 0 : getSku().hashCode());
        result = prime * result + ((getSkuKey() == null) ? 0 : getSkuKey().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + (isDeleteFlag() ? 1231 : 1237);
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}