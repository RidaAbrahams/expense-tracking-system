package za.co.rssa.ets.business.expense.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "Expense")
@NamedQuery(name = Expense.findAll, query = "SELECT e FROM Expense e")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Expense implements Serializable {

    static final String PREFIX = "expense.entity.Expense.";
    public static final String findAll = PREFIX + "findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;
    private String name;
    private String frequency;
    private String occurrence;
    private BigDecimal amount;

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Expense{" + "expenseId=" + expenseId + ", name=" + name + ", frequency=" + frequency + ", occurrence=" + occurrence + ", amount=" + amount + '}';
    }
    
}
