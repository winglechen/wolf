package study.daydayup.wolf.business.goods.biz.dal.dataobject;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class GoodsDO implements Serializable {
    private Long id;

    private Long orgId;

    private Integer categoryId;

    private Integer goodsType;

    private String name;

    private Long price;

    private Integer currency;

    private Integer chargeUnit;

    private Integer state;

    private Integer stockType;

    private Integer skuType;

    private String vsPrice;

    private String feature;

    private String mainPic;

    private String mainVideo;

    private String code;

    private String tags;

    private Long creator;

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
        sb.append(", orgId=").append(orgId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", goodsType=").append(goodsType);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        sb.append(", currency=").append(currency);
        sb.append(", chargeUnit=").append(chargeUnit);
        sb.append(", state=").append(state);
        sb.append(", stockType=").append(stockType);
        sb.append(", skuType=").append(skuType);
        sb.append(", vsPrice=").append(vsPrice);
        sb.append(", feature=").append(feature);
        sb.append(", mainPic=").append(mainPic);
        sb.append(", mainVideo=").append(mainVideo);
        sb.append(", code=").append(code);
        sb.append(", tags=").append(tags);
        sb.append(", creator=").append(creator);
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
        GoodsDO other = (GoodsDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getGoodsType() == null ? other.getGoodsType() == null : this.getGoodsType().equals(other.getGoodsType()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getChargeUnit() == null ? other.getChargeUnit() == null : this.getChargeUnit().equals(other.getChargeUnit()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getStockType() == null ? other.getStockType() == null : this.getStockType().equals(other.getStockType()))
            && (this.getSkuType() == null ? other.getSkuType() == null : this.getSkuType().equals(other.getSkuType()))
            && (this.getVsPrice() == null ? other.getVsPrice() == null : this.getVsPrice().equals(other.getVsPrice()))
            && (this.getFeature() == null ? other.getFeature() == null : this.getFeature().equals(other.getFeature()))
            && (this.getMainPic() == null ? other.getMainPic() == null : this.getMainPic().equals(other.getMainPic()))
            && (this.getMainVideo() == null ? other.getMainVideo() == null : this.getMainVideo().equals(other.getMainVideo()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
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
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getGoodsType() == null) ? 0 : getGoodsType().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getChargeUnit() == null) ? 0 : getChargeUnit().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getStockType() == null) ? 0 : getStockType().hashCode());
        result = prime * result + ((getSkuType() == null) ? 0 : getSkuType().hashCode());
        result = prime * result + ((getVsPrice() == null) ? 0 : getVsPrice().hashCode());
        result = prime * result + ((getFeature() == null) ? 0 : getFeature().hashCode());
        result = prime * result + ((getMainPic() == null) ? 0 : getMainPic().hashCode());
        result = prime * result + ((getMainVideo() == null) ? 0 : getMainVideo().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + (isDeleteFlag() ? 1231 : 1237);
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}