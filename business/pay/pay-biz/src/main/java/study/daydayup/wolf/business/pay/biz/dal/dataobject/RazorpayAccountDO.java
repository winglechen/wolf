package study.daydayup.wolf.business.pay.biz.dal.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RazorpayAccountDO implements Serializable {
    private Long id;

    private Long payerId;

    private String payerName;

    private Long payeeId;

    private String payeeName;

    private Integer state;

    private String contactId;

    private String contact;

    private String email;

    private String contactType;

    private Boolean contactActive;

    private String accountId;

    private String accountType;

    private String accountName;

    private String bankName;

    private String accountIfsc;

    private String accountNumber;

    private Boolean accountActive;

    private String vpaAddress;

    private String cardName;

    private String cardLast4;

    private String cardNetwork;

    private String cardType;

    private String cardIssuer;

    private String tags;

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
        sb.append(", payerId=").append(payerId);
        sb.append(", payerName=").append(payerName);
        sb.append(", payeeId=").append(payeeId);
        sb.append(", payeeName=").append(payeeName);
        sb.append(", state=").append(state);
        sb.append(", contactId=").append(contactId);
        sb.append(", contact=").append(contact);
        sb.append(", email=").append(email);
        sb.append(", contactType=").append(contactType);
        sb.append(", contactActive=").append(contactActive);
        sb.append(", accountId=").append(accountId);
        sb.append(", accountType=").append(accountType);
        sb.append(", accountName=").append(accountName);
        sb.append(", bankName=").append(bankName);
        sb.append(", accountIfsc=").append(accountIfsc);
        sb.append(", accountNumber=").append(accountNumber);
        sb.append(", accountActive=").append(accountActive);
        sb.append(", vpaAddress=").append(vpaAddress);
        sb.append(", cardName=").append(cardName);
        sb.append(", cardLast4=").append(cardLast4);
        sb.append(", cardNetwork=").append(cardNetwork);
        sb.append(", cardType=").append(cardType);
        sb.append(", cardIssuer=").append(cardIssuer);
        sb.append(", tags=").append(tags);
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
        RazorpayAccountDO other = (RazorpayAccountDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPayerId() == null ? other.getPayerId() == null : this.getPayerId().equals(other.getPayerId()))
            && (this.getPayerName() == null ? other.getPayerName() == null : this.getPayerName().equals(other.getPayerName()))
            && (this.getPayeeId() == null ? other.getPayeeId() == null : this.getPayeeId().equals(other.getPayeeId()))
            && (this.getPayeeName() == null ? other.getPayeeName() == null : this.getPayeeName().equals(other.getPayeeName()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getContactId() == null ? other.getContactId() == null : this.getContactId().equals(other.getContactId()))
            && (this.getContact() == null ? other.getContact() == null : this.getContact().equals(other.getContact()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getContactType() == null ? other.getContactType() == null : this.getContactType().equals(other.getContactType()))
            && (this.getContactActive() == null ? other.getContactActive() == null : this.getContactActive().equals(other.getContactActive()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getAccountType() == null ? other.getAccountType() == null : this.getAccountType().equals(other.getAccountType()))
            && (this.getAccountName() == null ? other.getAccountName() == null : this.getAccountName().equals(other.getAccountName()))
            && (this.getBankName() == null ? other.getBankName() == null : this.getBankName().equals(other.getBankName()))
            && (this.getAccountIfsc() == null ? other.getAccountIfsc() == null : this.getAccountIfsc().equals(other.getAccountIfsc()))
            && (this.getAccountNumber() == null ? other.getAccountNumber() == null : this.getAccountNumber().equals(other.getAccountNumber()))
            && (this.getAccountActive() == null ? other.getAccountActive() == null : this.getAccountActive().equals(other.getAccountActive()))
            && (this.getVpaAddress() == null ? other.getVpaAddress() == null : this.getVpaAddress().equals(other.getVpaAddress()))
            && (this.getCardName() == null ? other.getCardName() == null : this.getCardName().equals(other.getCardName()))
            && (this.getCardLast4() == null ? other.getCardLast4() == null : this.getCardLast4().equals(other.getCardLast4()))
            && (this.getCardNetwork() == null ? other.getCardNetwork() == null : this.getCardNetwork().equals(other.getCardNetwork()))
            && (this.getCardType() == null ? other.getCardType() == null : this.getCardType().equals(other.getCardType()))
            && (this.getCardIssuer() == null ? other.getCardIssuer() == null : this.getCardIssuer().equals(other.getCardIssuer()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPayerId() == null) ? 0 : getPayerId().hashCode());
        result = prime * result + ((getPayerName() == null) ? 0 : getPayerName().hashCode());
        result = prime * result + ((getPayeeId() == null) ? 0 : getPayeeId().hashCode());
        result = prime * result + ((getPayeeName() == null) ? 0 : getPayeeName().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getContactId() == null) ? 0 : getContactId().hashCode());
        result = prime * result + ((getContact() == null) ? 0 : getContact().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getContactType() == null) ? 0 : getContactType().hashCode());
        result = prime * result + ((getContactActive() == null) ? 0 : getContactActive().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getAccountType() == null) ? 0 : getAccountType().hashCode());
        result = prime * result + ((getAccountName() == null) ? 0 : getAccountName().hashCode());
        result = prime * result + ((getBankName() == null) ? 0 : getBankName().hashCode());
        result = prime * result + ((getAccountIfsc() == null) ? 0 : getAccountIfsc().hashCode());
        result = prime * result + ((getAccountNumber() == null) ? 0 : getAccountNumber().hashCode());
        result = prime * result + ((getAccountActive() == null) ? 0 : getAccountActive().hashCode());
        result = prime * result + ((getVpaAddress() == null) ? 0 : getVpaAddress().hashCode());
        result = prime * result + ((getCardName() == null) ? 0 : getCardName().hashCode());
        result = prime * result + ((getCardLast4() == null) ? 0 : getCardLast4().hashCode());
        result = prime * result + ((getCardNetwork() == null) ? 0 : getCardNetwork().hashCode());
        result = prime * result + ((getCardType() == null) ? 0 : getCardType().hashCode());
        result = prime * result + ((getCardIssuer() == null) ? 0 : getCardIssuer().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        return result;
    }
}