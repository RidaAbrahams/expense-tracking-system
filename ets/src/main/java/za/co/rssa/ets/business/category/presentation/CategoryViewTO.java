package za.co.rssa.ets.business.category.presentation;

import java.io.Serializable;
import za.co.rssa.ets.business.product.presentation.ScreenAction;

/**
 *
 * @author rida
 */
public class CategoryViewTO implements Serializable {
    
    private Long categoryId;
    private String categoryDescription;
    private String categoryType;
    private ScreenAction screenAction;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public ScreenAction getScreenAction() {
        return screenAction;
    }

    public void setScreenAction(ScreenAction screenAction) {
        this.screenAction = screenAction;
    }

    @Override
    public String toString() {
        return "CategoryViewTO{" + "categoryId=" + categoryId + ", categoryDescription=" + categoryDescription + ", categoryType=" + categoryType + ", screenAction=" + screenAction + '}';
    }

}
