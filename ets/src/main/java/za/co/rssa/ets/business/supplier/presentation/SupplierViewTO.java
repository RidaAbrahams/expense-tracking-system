package za.co.rssa.ets.business.supplier.presentation;

import java.io.Serializable;
import za.co.rssa.ets.business.common.presentation.ScreenAction;

/**
 *
 * @author rida
 */
public class SupplierViewTO implements Serializable {
    
    private Long supplierId;
    private String supplierName;
    private String supplierCategoryDescription;
    private Long supplierCategoryId;
    private ScreenAction screenAction;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierCategoryDescription() {
        return supplierCategoryDescription;
    }

    public void setSupplierCategoryDescription(String supplierCategoryDescription) {
        this.supplierCategoryDescription = supplierCategoryDescription;
    }

    public Long getSupplierCategoryId() {
        return supplierCategoryId;
    }

    public void setSupplierCategoryId(Long supplierCategoryId) {
        this.supplierCategoryId = supplierCategoryId;
    }

    public ScreenAction getScreenAction() {
        return screenAction;
    }

    public void setScreenAction(ScreenAction screenAction) {
        this.screenAction = screenAction;
    }

    @Override
    public String toString() {
        return "SupplierViewTO{" + "supplierId=" + supplierId + ", supplierName=" + supplierName + ", supplierCategoryDescription=" + supplierCategoryDescription + ", supplierCategoryId=" + supplierCategoryId + ", screenAction=" + screenAction + '}';
    }

}
