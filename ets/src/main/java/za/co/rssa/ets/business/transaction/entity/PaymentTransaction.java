package za.co.rssa.ets.business.transaction.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rida
 */
@Entity
@Table(name = "PaymentTransaction")
@NamedQuery(name = PaymentTransaction.findAll, query = "SELECT pt FROM PaymentTransaction pt")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentTransaction implements Serializable {

    static final String PREFIX = "transaction.entity.PaymentTransaction.";
    public static final String findAll = PREFIX + "findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentTransactionId;
    private Date transactionDate;
    private BigDecimal amount;
    private Long purchaseId;
    private Long expenseId;
    private Long supplierId;

    public Long getPaymentTransactionId() {
        return paymentTransactionId;
    }

    public void setPaymentTransactionId(Long paymentTransactionId) {
        this.paymentTransactionId = paymentTransactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "PaymentTransaction{" + "paymentTransactionId=" + paymentTransactionId + ", transactionDate=" + transactionDate + ", amount=" + amount + ", purchaseId=" + purchaseId + ", expenseId=" + expenseId + ", supplierId=" + supplierId + '}';
    }
    
}
