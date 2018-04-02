package za.co.rssa.ets.business.supplier.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import za.co.rssa.ets.business.category.boundary.CategoryService;
import za.co.rssa.ets.business.category.boundary.CategoryType;
import za.co.rssa.ets.business.category.entity.Category;
import za.co.rssa.ets.business.common.presentation.ScreenAction;
import za.co.rssa.ets.business.supplier.boundary.SupplierService;
import za.co.rssa.ets.business.supplier.entity.Supplier;

/**
 *
 * @author rida
 */
@ManagedBean
@SessionScoped
public class SupplierListView implements Serializable {

    @EJB
    private SupplierService supplierService;
    @EJB
    private CategoryService supplierCategoryService;
    private SupplierViewTO currentRowInTable;
    private String searchSupplierName;
    private String searchSupplierCategory;

    public String searchButtonAction() {
        getSuppliers();
        return "supplierList.xhtml?faces-redirect=true";
    }

    public String addButtonAction() {
        System.out.println("addButtonAction initiated...");
        return "supplierDetail.xhtml";
    }

    public String deleteButtonAction() {
        System.out.println("deleteButtonAction initiated...");
        Supplier currentSupplier = supplierService.findById(currentRowInTable.getSupplierId());
        supplierService.delete(currentSupplier);
        return "supplierList.xhtml?faces-redirect=true";
    }

    public String backButtonAction() {
        return "/index.xhtml?faces-redirect=true";
    }

    public Map<String, String> getSupplierCategories() {
        Map<String, String> result = new HashMap<>();
        List<Category> allSupplierCategories = supplierCategoryService.findByType(CategoryType.SUPPLIER.getName());
        for (Category supplierCategory : allSupplierCategories) {
            result.put(supplierCategory.getDescription(), supplierCategory.getCategoryId().toString());
        }
        return result;
    }

    public List<SupplierViewTO> getSuppliers() {
        List<SupplierViewTO> result = new ArrayList<>();
        List<Supplier> allSuppliers = supplierService.findSuppliersBy(searchSupplierName, searchSupplierCategory);
        populateViewWithDBResults(allSuppliers, result);
        return result;
    }

    private void populateViewWithDBResults(List<Supplier> allSuppliers, List<SupplierViewTO> result) {
        for (Supplier supplier : allSuppliers) {
            SupplierViewTO supplierViewTO = new SupplierViewTO();
            supplierViewTO.setSupplierId(supplier.getSupplierId());
            supplierViewTO.setSupplierName(supplier.getName());
            supplierViewTO.setSupplierCategoryId(supplier.getSupplierCategory().getCategoryId());
            supplierViewTO.setSupplierCategoryDescription(supplier.getSupplierCategory().getDescription());
            supplierViewTO.setScreenAction(ScreenAction.EDIT);
            result.add(supplierViewTO);
        }
    }

    public SupplierViewTO getCurrentRowInTable() {
        return currentRowInTable;
    }

    public void setCurrentRowInTable(SupplierViewTO currentRowInTable) {
        this.currentRowInTable = currentRowInTable;
    }

    public String getSearchSupplierName() {
        return searchSupplierName;
    }

    public void setSearchSupplierName(String searchSupplierName) {
        this.searchSupplierName = searchSupplierName;
    }

    public String getSearchSupplierCategory() {
        return searchSupplierCategory;
    }

    public void setSearchSupplierCategory(String searchSupplierCategory) {
        this.searchSupplierCategory = searchSupplierCategory;
    }
    
}
