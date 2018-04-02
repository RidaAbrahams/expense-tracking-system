package za.co.rssa.ets.business.supplier.presentation;

import za.co.rssa.ets.business.supplier.presentation.*;
import za.co.rssa.ets.business.common.presentation.ScreenAction;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import za.co.rssa.ets.business.category.boundary.CategoryService;
import za.co.rssa.ets.business.category.boundary.CategoryType;
import za.co.rssa.ets.business.supplier.boundary.SupplierService;
import za.co.rssa.ets.business.supplier.entity.Supplier;
import za.co.rssa.ets.business.category.entity.Category;

/**
 *
 * @author rida
 */
@ManagedBean
@SessionScoped
public class SupplierDetailView implements Serializable {

    @EJB
    private SupplierService supplierService;
    @EJB
    private CategoryService supplierCategoryService;
    private SupplierViewTO selectedSupplier;
    private ScreenAction currentScreenAction;

    @PostConstruct
    public void init() {
        if (selectedSupplier == null) {
            selectedSupplier = new SupplierViewTO();
        }
    }

    public String addButtonAction() {
        System.out.println("addButtonAction initiated...");
        selectedSupplier = new SupplierViewTO();
        currentScreenAction = ScreenAction.ADD;
        System.out.println("currentScreenAction = " + currentScreenAction);
        return "supplierDetail.xhtml";
    }

    public String editButtonAction() {
        System.out.println("editButtonAction initiated...");
        currentScreenAction = ScreenAction.EDIT;
        System.out.println("currentScreenAction = " + currentScreenAction);
        return "supplierDetail.xhtml";
    }

    public SupplierViewTO getSelectedSupplier() {
        return selectedSupplier;
    }

    public void setSelectedSupplier(SupplierViewTO selectedSupplier) {
        this.selectedSupplier = selectedSupplier;
        System.out.println("************************** setSelected() ran, the value of ScreenAction is: " + this.selectedSupplier.getScreenAction());
    }

    public ScreenAction getCurrentScreenAction() {
        return currentScreenAction;
    }

    public void setCurrentScreenAction(ScreenAction currentScreenAction) {
        this.currentScreenAction = currentScreenAction;
    }

    public Map<String, String> getSupplierCategories() {
        Map<String, String> result = new HashMap<>();
        List<Category> allSupplierCategories = supplierCategoryService.findByType(CategoryType.SUPPLIER.getName());
        for (Category supplierCategory : allSupplierCategories) {
            result.put(supplierCategory.getDescription(), supplierCategory.getCategoryId().toString());
        }
        return result;
    }

    public String saveButtonAction() {
        System.out.println("******************** Saving... currentScreenAction = " + currentScreenAction);
        System.out.println("In saveButtonAction, the value of selectedSupplier = " + selectedSupplier);
        if (currentScreenAction.equals(ScreenAction.ADD)) {
            System.out.println("***************** About to ADD *********************");
            Supplier supplier = new Supplier();
            supplier.setName(   selectedSupplier.getSupplierName());
            supplierService.save(supplier, selectedSupplier.getSupplierCategoryId());
        } else if (currentScreenAction.equals(ScreenAction.EDIT)) {
            System.out.println("***************** About to EDIT *********************");
            Supplier selectedSupplierFromDB = supplierService.findById(selectedSupplier.getSupplierId());
            selectedSupplierFromDB.setName(selectedSupplier.getSupplierName());
            supplierService.save(selectedSupplierFromDB, selectedSupplier.getSupplierCategoryId());
        }
        return "supplierList.xhtml?faces-redirect=true";
    }

    public String cancelButtonAction() {
        return "supplierList.xhtml?faces-redirect=true";
    }

    private void addMessage(String messageToDisplay) {
        FacesMessage messaage = new FacesMessage(FacesMessage.SEVERITY_INFO, messageToDisplay, null);
        FacesContext.getCurrentInstance().addMessage(null, messaage);
    }

}
