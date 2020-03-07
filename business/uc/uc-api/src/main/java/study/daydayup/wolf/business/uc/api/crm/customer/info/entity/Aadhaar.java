package study.daydayup.wolf.business.uc.api.crm.customer.info.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.time.LocalDateTime;

@Data
public class Aadhaar implements Model {
    private Long id;

    private Long accountId;

    private Long orgId;

    private String aadhaarNo;

    private String uid;

    private String gender;

    private String dob;

    private String name;

    private String address;

    private String frontSide;

    private String backSide;

    private String vid;

    private String yob;

    private String co;

    private String loc;

    private String vtc;

    private String po;

    private String dist;

    private String subdist;

    private String state;

    private String pc;

    private Boolean deleteFlag;

    private Integer version;

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
        sb.append(", accountId=").append(accountId);
        sb.append(", orgId=").append(orgId);
        sb.append(", aadhaarNo=").append(aadhaarNo);
        sb.append(", uid=").append(uid);
        sb.append(", gender=").append(gender);
        sb.append(", dob=").append(dob);
        sb.append(", name=").append(name);
        sb.append(", address=").append(address);
        sb.append(", frontSide=").append(frontSide);
        sb.append(", backSide=").append(backSide);
        sb.append(", vid=").append(vid);
        sb.append(", yob=").append(yob);
        sb.append(", co=").append(co);
        sb.append(", loc=").append(loc);
        sb.append(", vtc=").append(vtc);
        sb.append(", po=").append(po);
        sb.append(", dist=").append(dist);
        sb.append(", subdist=").append(subdist);
        sb.append(", state=").append(state);
        sb.append(", pc=").append(pc);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", version=").append(version);
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
        Aadhaar other = (Aadhaar) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getAadhaarNo() == null ? other.getAadhaarNo() == null : this.getAadhaarNo().equals(other.getAadhaarNo()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getDob() == null ? other.getDob() == null : this.getDob().equals(other.getDob()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getFrontSide() == null ? other.getFrontSide() == null : this.getFrontSide().equals(other.getFrontSide()))
            && (this.getBackSide() == null ? other.getBackSide() == null : this.getBackSide().equals(other.getBackSide()))
            && (this.getVid() == null ? other.getVid() == null : this.getVid().equals(other.getVid()))
            && (this.getYob() == null ? other.getYob() == null : this.getYob().equals(other.getYob()))
            && (this.getCo() == null ? other.getCo() == null : this.getCo().equals(other.getCo()))
            && (this.getLoc() == null ? other.getLoc() == null : this.getLoc().equals(other.getLoc()))
            && (this.getVtc() == null ? other.getVtc() == null : this.getVtc().equals(other.getVtc()))
            && (this.getPo() == null ? other.getPo() == null : this.getPo().equals(other.getPo()))
            && (this.getDist() == null ? other.getDist() == null : this.getDist().equals(other.getDist()))
            && (this.getSubdist() == null ? other.getSubdist() == null : this.getSubdist().equals(other.getSubdist()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getPc() == null ? other.getPc() == null : this.getPc().equals(other.getPc()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getLastEditor() == null ? other.getLastEditor() == null : this.getLastEditor().equals(other.getLastEditor()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getAadhaarNo() == null) ? 0 : getAadhaarNo().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getDob() == null) ? 0 : getDob().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getFrontSide() == null) ? 0 : getFrontSide().hashCode());
        result = prime * result + ((getBackSide() == null) ? 0 : getBackSide().hashCode());
        result = prime * result + ((getVid() == null) ? 0 : getVid().hashCode());
        result = prime * result + ((getYob() == null) ? 0 : getYob().hashCode());
        result = prime * result + ((getCo() == null) ? 0 : getCo().hashCode());
        result = prime * result + ((getLoc() == null) ? 0 : getLoc().hashCode());
        result = prime * result + ((getVtc() == null) ? 0 : getVtc().hashCode());
        result = prime * result + ((getPo() == null) ? 0 : getPo().hashCode());
        result = prime * result + ((getDist() == null) ? 0 : getDist().hashCode());
        result = prime * result + ((getSubdist() == null) ? 0 : getSubdist().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getPc() == null) ? 0 : getPc().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}