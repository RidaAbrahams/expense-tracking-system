package za.co.rssa.ets.business.product.presentation;

import java.io.Serializable;

/**
 *
 * @author rida
 */
public class ProductViewTO implements Serializable {
    
    private Long productId;
    private String productDescription;
    private String productCategoryDescription;
    private Long productCategoryId;
    private ScreenAction screenAction;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductCategoryDescription() {
        return productCategoryDescription;
    }

    public void setProductCategoryDescription(String productCategoryDescription) {
        this.productCategoryDescription = productCategoryDescription;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public ScreenAction getScreenAction() {
        return screenAction;
    }

    public void setScreenAction(ScreenAction screenAction) {
        this.screenAction = screenAction;
    }

    @Override
    public String toString() {
        return "ProductViewTO{" + "productId=" + productId + ", productDescription=" + productDescription + ", productCategoryDescription=" + productCategoryDescription + ", productCategoryId=" + productCategoryId + ", screenAction=" + screenAction + '}';
    }
}
