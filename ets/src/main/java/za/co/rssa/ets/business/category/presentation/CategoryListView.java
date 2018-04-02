package za.co.rssa.ets.business.category.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import za.co.rssa.ets.business.category.boundary.CategoryService;
import za.co.rssa.ets.business.category.boundary.CategoryType;
import za.co.rssa.ets.business.category.entity.Category;
import za.co.rssa.ets.business.product.boundary.ProductService;
import za.co.rssa.ets.business.product.entity.Product;
import za.co.rssa.ets.business.common.presentation.ScreenAction;
import za.co.rssa.ets.business.supplier.boundary.SupplierService;
import za.co.rssa.ets.business.supplier.entity.Supplier;

/**
 *
 * @author rida
 */
@ManagedBean
@SessionScoped
public class CategoryListView implements Serializable {

    @EJB
    private CategoryService categoryService;
    @EJB
    private ProductService productService;
    @EJB
    private SupplierService supplierService;
    private CategoryViewTO currentRowInTable;
    private String searchCategoryDescription;
    private String searchCategoryType;

    public String searchButtonAction() {
        getCategories();
        return "categoryList.xhtml?faces-redirect=true";
    }

    public String addButtonAction() {
        System.out.println("addButtonAction initiated...");
        return "categoryDetail.xhtml";
    }

    public String deleteButtonAction() {
        String result = null;

        if (categoryInUseByOneOrMoreProducts()) {
            System.out.println("May not delete this category!!! Products are using it");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "This category is in use. First delete all products linked to it before attempting to delete it again."));
            result = null;
        } else if (categoryInUseByOneOrMoreSuppliers()) {
            System.out.println("May not delete this category!!! Suppliers are using it");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "This category is in use. First delete all suppliers linked to it before attempting to delete it again."));
            result = null;
        } else {
            deleteCategory();
            result = "categoryList.xhtml?faces-redirect=true";
        }
        return result;
    }

    private void deleteCategory() {
        Category currentCategory = categoryService.findById(currentRowInTable.getCategoryId());
        categoryService.delete(currentCategory);
    }

    private boolean categoryInUseByOneOrMoreProducts() {
        List<Product> productsLinkedToThisCategory = productService.findProductsBy(null, currentRowInTable.getCategoryId().toString());
        // If there aren't any products linked to this category, return false, else return true.
        if (productsLinkedToThisCategory == null || productsLinkedToThisCategory.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean categoryInUseByOneOrMoreSuppliers() {
        List<Supplier> suppliersLinkedToThisCategory = supplierService.findSuppliersBy(null, currentRowInTable.getCategoryId().toString());
        // If there aren't any suppliers linked to this category, return false, else return true.
        if (suppliersLinkedToThisCategory == null || suppliersLinkedToThisCategory.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public String backButtonAction() {
        return "/index.xhtml?faces-redirect=true";
    }

    public Map<String, String> getCategoryTypes() {
        Map<String, String> result = new HashMap<>();
        result.put(CategoryType.SUPPLIER.getName(), CategoryType.SUPPLIER.getName());
        result.put(CategoryType.PRODUCT.getName(), CategoryType.PRODUCT.getName());
        return result;
    }

    public List<CategoryViewTO> getCategories() {
        List<CategoryViewTO> result = new ArrayList<>();
        List<Category> allProducts = categoryService.findCategoriesBy(searchCategoryDescription, searchCategoryType);
        populateViewWithDBResults(allProducts, result);
        return result;
    }

    private void populateViewWithDBResults(List<Category> allCategories, List<CategoryViewTO> result) {
        for (Category category : allCategories) {
            CategoryViewTO categoryViewTO = new CategoryViewTO();
            categoryViewTO.setCategoryId(category.getCategoryId());
            categoryViewTO.setCategoryDescription(category.getDescription());
            categoryViewTO.setCategoryType(category.getType());
            categoryViewTO.setScreenAction(ScreenAction.EDIT);
            result.add(categoryViewTO);
        }
    }

    public String getSearchCategoryDescription() {
        return searchCategoryDescription;
    }

    public void setSearchCategoryDescription(String searchCategoryDescription) {
        this.searchCategoryDescription = searchCategoryDescription;
    }

    public String getSearchCategoryType() {
        return searchCategoryType;
    }

    public void setSearchCategoryType(String searchCategoryType) {
        this.searchCategoryType = searchCategoryType;
    }

    public CategoryViewTO getCurrentRowInTable() {
        return currentRowInTable;
    }

    public void setCurrentRowInTable(CategoryViewTO currentRowInTable) {
        this.currentRowInTable = currentRowInTable;
    }

}
