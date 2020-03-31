package study.daydayup.wolf.business.uc.setting.biz.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CompanyStatusDO implements Serializable {
    private Long id;

    private Long orgId;

    private Long s1;

    private Long s2;

    private Long s3;

    private Long s4;

    private Long s5;

    private Long s6;

    private Long s7;

    private Long s8;

    private Long s9;

    private Long s10;

    private Long s11;

    private Long s12;

    private Long s13;

    private Long s14;

    private Long s15;

    private Long s16;

    private Long s17;

    private Long s18;

    private Long s19;

    private Long s20;

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
        sb.append(", orgId=").append(orgId);
        sb.append(", s1=").append(s1);
        sb.append(", s2=").append(s2);
        sb.append(", s3=").append(s3);
        sb.append(", s4=").append(s4);
        sb.append(", s5=").append(s5);
        sb.append(", s6=").append(s6);
        sb.append(", s7=").append(s7);
        sb.append(", s8=").append(s8);
        sb.append(", s9=").append(s9);
        sb.append(", s10=").append(s10);
        sb.append(", s11=").append(s11);
        sb.append(", s12=").append(s12);
        sb.append(", s13=").append(s13);
        sb.append(", s14=").append(s14);
        sb.append(", s15=").append(s15);
        sb.append(", s16=").append(s16);
        sb.append(", s17=").append(s17);
        sb.append(", s18=").append(s18);
        sb.append(", s19=").append(s19);
        sb.append(", s20=").append(s20);
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
        CompanyStatusDO other = (CompanyStatusDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getS1() == null ? other.getS1() == null : this.getS1().equals(other.getS1()))
            && (this.getS2() == null ? other.getS2() == null : this.getS2().equals(other.getS2()))
            && (this.getS3() == null ? other.getS3() == null : this.getS3().equals(other.getS3()))
            && (this.getS4() == null ? other.getS4() == null : this.getS4().equals(other.getS4()))
            && (this.getS5() == null ? other.getS5() == null : this.getS5().equals(other.getS5()))
            && (this.getS6() == null ? other.getS6() == null : this.getS6().equals(other.getS6()))
            && (this.getS7() == null ? other.getS7() == null : this.getS7().equals(other.getS7()))
            && (this.getS8() == null ? other.getS8() == null : this.getS8().equals(other.getS8()))
            && (this.getS9() == null ? other.getS9() == null : this.getS9().equals(other.getS9()))
            && (this.getS10() == null ? other.getS10() == null : this.getS10().equals(other.getS10()))
            && (this.getS11() == null ? other.getS11() == null : this.getS11().equals(other.getS11()))
            && (this.getS12() == null ? other.getS12() == null : this.getS12().equals(other.getS12()))
            && (this.getS13() == null ? other.getS13() == null : this.getS13().equals(other.getS13()))
            && (this.getS14() == null ? other.getS14() == null : this.getS14().equals(other.getS14()))
            && (this.getS15() == null ? other.getS15() == null : this.getS15().equals(other.getS15()))
            && (this.getS16() == null ? other.getS16() == null : this.getS16().equals(other.getS16()))
            && (this.getS17() == null ? other.getS17() == null : this.getS17().equals(other.getS17()))
            && (this.getS18() == null ? other.getS18() == null : this.getS18().equals(other.getS18()))
            && (this.getS19() == null ? other.getS19() == null : this.getS19().equals(other.getS19()))
            && (this.getS20() == null ? other.getS20() == null : this.getS20().equals(other.getS20()))
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
        result = prime * result + ((getS1() == null) ? 0 : getS1().hashCode());
        result = prime * result + ((getS2() == null) ? 0 : getS2().hashCode());
        result = prime * result + ((getS3() == null) ? 0 : getS3().hashCode());
        result = prime * result + ((getS4() == null) ? 0 : getS4().hashCode());
        result = prime * result + ((getS5() == null) ? 0 : getS5().hashCode());
        result = prime * result + ((getS6() == null) ? 0 : getS6().hashCode());
        result = prime * result + ((getS7() == null) ? 0 : getS7().hashCode());
        result = prime * result + ((getS8() == null) ? 0 : getS8().hashCode());
        result = prime * result + ((getS9() == null) ? 0 : getS9().hashCode());
        result = prime * result + ((getS10() == null) ? 0 : getS10().hashCode());
        result = prime * result + ((getS11() == null) ? 0 : getS11().hashCode());
        result = prime * result + ((getS12() == null) ? 0 : getS12().hashCode());
        result = prime * result + ((getS13() == null) ? 0 : getS13().hashCode());
        result = prime * result + ((getS14() == null) ? 0 : getS14().hashCode());
        result = prime * result + ((getS15() == null) ? 0 : getS15().hashCode());
        result = prime * result + ((getS16() == null) ? 0 : getS16().hashCode());
        result = prime * result + ((getS17() == null) ? 0 : getS17().hashCode());
        result = prime * result + ((getS18() == null) ? 0 : getS18().hashCode());
        result = prime * result + ((getS19() == null) ? 0 : getS19().hashCode());
        result = prime * result + ((getS20() == null) ? 0 : getS20().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + (isDeleteFlag() ? 1231 : 1237);
        result = prime * result + ((getLastEditor() == null) ? 0 : getLastEditor().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}