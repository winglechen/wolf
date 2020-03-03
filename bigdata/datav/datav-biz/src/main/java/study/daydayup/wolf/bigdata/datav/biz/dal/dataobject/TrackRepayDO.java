package study.daydayup.wolf.bigdata.datav.biz.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TrackRepayDO implements Serializable {
    private Long id;

    private Long orgId;

    private LocalDate loanDate;

    private LocalDate dueDate;

    private Long goodsId;

    private Integer installmentNo;

    private Integer prepayCount;

    private BigDecimal prepayAmount;

    private Integer repayCount;

    private BigDecimal repayAmount;

    private Integer overdueCount;

    private BigDecimal overdueAmount;

    private Integer lossCount;

    private BigDecimal lossAmount;

    private Integer partlyLossCount;

    private BigDecimal partlyLossAmount;

    private BigDecimal d1Amount;

    private Integer d1;

    private Integer d2;

    private Integer d3;

    private Integer d4;

    private Integer d5;

    private Integer d6;

    private Integer d7;

    private Integer w2;

    private Integer w3;

    private Integer w4;

    private Integer m1;

    private Integer mn;

    private Boolean deleteFlag;

    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orgId=").append(orgId);
        sb.append(", loanDate=").append(loanDate);
        sb.append(", dueDate=").append(dueDate);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", installmentNo=").append(installmentNo);
        sb.append(", prepayCount=").append(prepayCount);
        sb.append(", prepayAmount=").append(prepayAmount);
        sb.append(", repayCount=").append(repayCount);
        sb.append(", repayAmount=").append(repayAmount);
        sb.append(", overdueCount=").append(overdueCount);
        sb.append(", overdueAmount=").append(overdueAmount);
        sb.append(", lossCount=").append(lossCount);
        sb.append(", lossAmount=").append(lossAmount);
        sb.append(", partlyLossCount=").append(partlyLossCount);
        sb.append(", partlyLossAmount=").append(partlyLossAmount);
        sb.append(", d1Amount=").append(d1Amount);
        sb.append(", d1=").append(d1);
        sb.append(", d2=").append(d2);
        sb.append(", d3=").append(d3);
        sb.append(", d4=").append(d4);
        sb.append(", d5=").append(d5);
        sb.append(", d6=").append(d6);
        sb.append(", d7=").append(d7);
        sb.append(", w2=").append(w2);
        sb.append(", w3=").append(w3);
        sb.append(", w4=").append(w4);
        sb.append(", m1=").append(m1);
        sb.append(", mn=").append(mn);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", createdAt=").append(createdAt);
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
        TrackRepayDO other = (TrackRepayDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getLoanDate() == null ? other.getLoanDate() == null : this.getLoanDate().equals(other.getLoanDate()))
            && (this.getDueDate() == null ? other.getDueDate() == null : this.getDueDate().equals(other.getDueDate()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getInstallmentNo() == null ? other.getInstallmentNo() == null : this.getInstallmentNo().equals(other.getInstallmentNo()))
            && (this.getPrepayCount() == null ? other.getPrepayCount() == null : this.getPrepayCount().equals(other.getPrepayCount()))
            && (this.getPrepayAmount() == null ? other.getPrepayAmount() == null : this.getPrepayAmount().equals(other.getPrepayAmount()))
            && (this.getRepayCount() == null ? other.getRepayCount() == null : this.getRepayCount().equals(other.getRepayCount()))
            && (this.getRepayAmount() == null ? other.getRepayAmount() == null : this.getRepayAmount().equals(other.getRepayAmount()))
            && (this.getOverdueCount() == null ? other.getOverdueCount() == null : this.getOverdueCount().equals(other.getOverdueCount()))
            && (this.getOverdueAmount() == null ? other.getOverdueAmount() == null : this.getOverdueAmount().equals(other.getOverdueAmount()))
            && (this.getLossCount() == null ? other.getLossCount() == null : this.getLossCount().equals(other.getLossCount()))
            && (this.getLossAmount() == null ? other.getLossAmount() == null : this.getLossAmount().equals(other.getLossAmount()))
            && (this.getPartlyLossCount() == null ? other.getPartlyLossCount() == null : this.getPartlyLossCount().equals(other.getPartlyLossCount()))
            && (this.getPartlyLossAmount() == null ? other.getPartlyLossAmount() == null : this.getPartlyLossAmount().equals(other.getPartlyLossAmount()))
            && (this.getD1Amount() == null ? other.getD1Amount() == null : this.getD1Amount().equals(other.getD1Amount()))
            && (this.getD1() == null ? other.getD1() == null : this.getD1().equals(other.getD1()))
            && (this.getD2() == null ? other.getD2() == null : this.getD2().equals(other.getD2()))
            && (this.getD3() == null ? other.getD3() == null : this.getD3().equals(other.getD3()))
            && (this.getD4() == null ? other.getD4() == null : this.getD4().equals(other.getD4()))
            && (this.getD5() == null ? other.getD5() == null : this.getD5().equals(other.getD5()))
            && (this.getD6() == null ? other.getD6() == null : this.getD6().equals(other.getD6()))
            && (this.getD7() == null ? other.getD7() == null : this.getD7().equals(other.getD7()))
            && (this.getW2() == null ? other.getW2() == null : this.getW2().equals(other.getW2()))
            && (this.getW3() == null ? other.getW3() == null : this.getW3().equals(other.getW3()))
            && (this.getW4() == null ? other.getW4() == null : this.getW4().equals(other.getW4()))
            && (this.getM1() == null ? other.getM1() == null : this.getM1().equals(other.getM1()))
            && (this.getMn() == null ? other.getMn() == null : this.getMn().equals(other.getMn()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getLoanDate() == null) ? 0 : getLoanDate().hashCode());
        result = prime * result + ((getDueDate() == null) ? 0 : getDueDate().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getInstallmentNo() == null) ? 0 : getInstallmentNo().hashCode());
        result = prime * result + ((getPrepayCount() == null) ? 0 : getPrepayCount().hashCode());
        result = prime * result + ((getPrepayAmount() == null) ? 0 : getPrepayAmount().hashCode());
        result = prime * result + ((getRepayCount() == null) ? 0 : getRepayCount().hashCode());
        result = prime * result + ((getRepayAmount() == null) ? 0 : getRepayAmount().hashCode());
        result = prime * result + ((getOverdueCount() == null) ? 0 : getOverdueCount().hashCode());
        result = prime * result + ((getOverdueAmount() == null) ? 0 : getOverdueAmount().hashCode());
        result = prime * result + ((getLossCount() == null) ? 0 : getLossCount().hashCode());
        result = prime * result + ((getLossAmount() == null) ? 0 : getLossAmount().hashCode());
        result = prime * result + ((getPartlyLossCount() == null) ? 0 : getPartlyLossCount().hashCode());
        result = prime * result + ((getPartlyLossAmount() == null) ? 0 : getPartlyLossAmount().hashCode());
        result = prime * result + ((getD1Amount() == null) ? 0 : getD1Amount().hashCode());
        result = prime * result + ((getD1() == null) ? 0 : getD1().hashCode());
        result = prime * result + ((getD2() == null) ? 0 : getD2().hashCode());
        result = prime * result + ((getD3() == null) ? 0 : getD3().hashCode());
        result = prime * result + ((getD4() == null) ? 0 : getD4().hashCode());
        result = prime * result + ((getD5() == null) ? 0 : getD5().hashCode());
        result = prime * result + ((getD6() == null) ? 0 : getD6().hashCode());
        result = prime * result + ((getD7() == null) ? 0 : getD7().hashCode());
        result = prime * result + ((getW2() == null) ? 0 : getW2().hashCode());
        result = prime * result + ((getW3() == null) ? 0 : getW3().hashCode());
        result = prime * result + ((getW4() == null) ? 0 : getW4().hashCode());
        result = prime * result + ((getM1() == null) ? 0 : getM1().hashCode());
        result = prime * result + ((getMn() == null) ? 0 : getMn().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}