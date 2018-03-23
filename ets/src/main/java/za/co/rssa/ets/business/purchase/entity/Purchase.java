package za.co.rssa.ets.business.purchase.entity;

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
@Table(name = "Purchase")
@NamedQuery(name = Purchase.findAll, query = "SELECT p FROM Purchase p")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Purchase implements Serializable {

    static final String PREFIX = "purchase.entity.Purchase.";
    public static final String findAll = PREFIX + "findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;
    private String product;
    private BigDecimal amount;
    private Date purchaseDate;
    private String comments;

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Purchase{" + "purchaseId=" + purchaseId + ", product=" + product + ", amount=" + amount + ", purchaseDate=" + purchaseDate + ", comments=" + comments + '}';
    }
    
}
