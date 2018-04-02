package za.co.rssa.ets.business.expense.presentation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author rida
 */
public class ExpenseListViewTO implements Serializable {
    
    private String monthOfPurchase;
    private Date transactionDate;
    private String productCategory;
    private String productDescription;
    private int unit;
    private String productSize;
    private BigDecimal purchaseAmount;
    private String comments;

    public String getMonthOfPurchase() {
        return monthOfPurchase;
    }

    public void setMonthOfPurchase(String monthOfPurchase) {
        this.monthOfPurchase = monthOfPurchase;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public BigDecimal getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(BigDecimal purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ExpenseListViewTO{" + "monthOfPurchase=" + monthOfPurchase + ", transactionDate=" + transactionDate + ", productCategory=" + productCategory + ", productDescription=" + productDescription + ", unit=" + unit + ", productSize=" + productSize + ", purchaseAmount=" + purchaseAmount + ", comments=" + comments + '}';
    }
    
}
