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
import za.co.rssa.ets.business.product.presentation.ScreenAction;

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
        System.out.println("deleteButtonAction initiated...");
        // Check if this category is in use before deleting it
        List<Product> productsLinkedToThisCategory = productService.findProductsBy(null, currentRowInTable.getCategoryId().toString());
        // If there aren't any products linked to this category, delete it, else display an error.
        if (productsLinkedToThisCategory == null || productsLinkedToThisCategory.isEmpty()) {
            System.out.println("About to delete this category...");
            Category currentCategory = categoryService.findById(currentRowInTable.getCategoryId());
            categoryService.delete(currentCategory);
            return "categoryList.xhtml?faces-redirect=true";
        } else {
            System.out.println("May not delete this category!!!");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "This category is in use. First delete all products linked to it before attempting to delete it again."));
            result = null;
        }
        return result;
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
