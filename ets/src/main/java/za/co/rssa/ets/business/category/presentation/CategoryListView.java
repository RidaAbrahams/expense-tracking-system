package za.co.rssa.ets.business.category.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import za.co.rssa.ets.business.category.boundary.CategoryService;
import za.co.rssa.ets.business.category.entity.Category;
import za.co.rssa.ets.business.product.presentation.ProductViewTO;
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
        System.out.println("deleteButtonAction initiated...");
        Category currentCategory = categoryService.findById(currentRowInTable.getCategoryId());
        categoryService.delete(currentCategory);
        return "categoryList.xhtml?faces-redirect=true";
    }

    public Map<String, String> getCategoryTypes() {
        Map<String, String> result = new HashMap<>();
        result.put("Supplier", "Supplier");
        result.put("Product", "Product");
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
