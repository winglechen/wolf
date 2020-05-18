package study.daydayup.wolf.business.goods.biz.dal.dataobject;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class GoodsDetailDO implements Serializable {
    private Long id;

    private Long orgId;

    private String pics;

    private String skuInfo;

    private String extInfo;

    private String detail;

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
        sb.append(", pics=").append(pics);
        sb.append(", skuInfo=").append(skuInfo);
        sb.append(", extInfo=").append(extInfo);
        sb.append(", detail=").append(detail);
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
        GoodsDetailDO other = (GoodsDetailDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getPics() == null ? other.getPics() == null : this.getPics().equals(other.getPics()))
            && (this.getSkuInfo() == null ? other.getSkuInfo() == null : this.getSkuInfo().equals(other.getSkuInfo()))
            && (this.getExtInfo() == null ? other.getExtInfo() == null : this.getExtInfo().equals(other.getExtInfo()))
            && (this.getDetail() == null ? other.getDetail() == null : this.getDetail().equals(other.getDetail()))
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
        result = prime * result + ((getPics() == null) ? 0 : getPics().hashCode());
        result = prime * result + ((getSkuInfo() == null) ? 0 : getSkuInfo().hashCode());
        result = prime * result + ((getExtInfo() == null) ? 0 : getExtInfo().hashCode());
        result = prime * result + ((getDetail() == null) ? 0 : getDetail().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + (isDeleteFlag() ? 1231 : 1237);
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}