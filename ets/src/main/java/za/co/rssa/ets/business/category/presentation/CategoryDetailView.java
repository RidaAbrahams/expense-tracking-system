package za.co.rssa.ets.business.category.presentation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import za.co.rssa.ets.business.category.boundary.CategoryService;
import za.co.rssa.ets.business.category.boundary.CategoryType;
import za.co.rssa.ets.business.category.entity.Category;
import za.co.rssa.ets.business.product.presentation.ScreenAction;

/**
 *
 * @author rida
 */
@ManagedBean
@SessionScoped
public class CategoryDetailView implements Serializable {

    @EJB
    private CategoryService categoryService;
    private CategoryViewTO selectedCategory;
    private ScreenAction currentScreenAction;

    @PostConstruct
    public void init() {
        if (selectedCategory == null) {
            selectedCategory = new CategoryViewTO();
        }
    }

    public String addButtonAction() {
        System.out.println("addButtonAction initiated...");
        selectedCategory = new CategoryViewTO();
        currentScreenAction = ScreenAction.ADD;
        System.out.println("currentScreenAction = " + currentScreenAction);
        return "categoryDetail.xhtml";
    }

    public String editButtonAction() {
        System.out.println("editButtonAction initiated...");
        currentScreenAction = ScreenAction.EDIT;
        System.out.println("currentScreenAction = " + currentScreenAction);
        return "categoryDetail.xhtml";
    }

    public CategoryViewTO getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(CategoryViewTO selectedCategory) {
        this.selectedCategory = selectedCategory;
        System.out.println("************************** setSelected() ran, the value of ScreenAction is: " + this.selectedCategory.getScreenAction());
    }

    public ScreenAction getCurrentScreenAction() {
        return currentScreenAction;
    }

    public void setCurrentScreenAction(ScreenAction currentScreenAction) {
        this.currentScreenAction = currentScreenAction;
    }

    public Map<String, String> getCategoryTypes() {
        Map<String, String> result = new HashMap<>();
        result.put(CategoryType.SUPPLIER.getName(), CategoryType.SUPPLIER.getName());
        result.put(CategoryType.PRODUCT.getName(), CategoryType.PRODUCT.getName());
        return result;
    }

    public String saveButtonAction() {
        System.out.println("******************** Saving... currentScreenAction = " + currentScreenAction);
        System.out.println("In saveButtonAction, the value of selectedProduct = " + selectedCategory);
        if (currentScreenAction.equals(ScreenAction.ADD)) {
            System.out.println("***************** About to ADD *********************");
            Category category = new Category();
            category.setDescription(selectedCategory.getCategoryDescription());
            category.setType(selectedCategory.getCategoryType());
            categoryService.save(category);
        } else if (currentScreenAction.equals(ScreenAction.EDIT)) {
            System.out.println("***************** About to EDIT *********************");
            Category selectedCategoryFromDB = categoryService.findById(selectedCategory.getCategoryId());
            selectedCategoryFromDB.setDescription(selectedCategory.getCategoryDescription());
            selectedCategoryFromDB.setType(selectedCategory.getCategoryType());
            categoryService.save(selectedCategoryFromDB);
        }
        return "categoryList.xhtml?faces-redirect=true";
    }

    public String cancelButtonAction() {
        return "categoryList.xhtml?faces-redirect=true";
    }
}
