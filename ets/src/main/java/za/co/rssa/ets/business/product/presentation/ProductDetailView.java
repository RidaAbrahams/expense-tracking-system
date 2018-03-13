package za.co.rssa.ets.business.product.presentation;

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
import za.co.rssa.ets.business.product.boundary.CategoryService;
import za.co.rssa.ets.business.product.boundary.ProductService;
import za.co.rssa.ets.business.product.entity.Product;
import za.co.rssa.ets.business.product.entity.Category;

/**
 *
 * @author rida
 */
@ManagedBean
@SessionScoped
public class ProductDetailView implements Serializable {

    @EJB
    private ProductService productService;
    @EJB
    private CategoryService productCategoryService;
    private ProductViewTO selectedProduct;
    private ScreenAction currentScreenAction;

    @PostConstruct
    public void init() {
        if (selectedProduct == null) {
            selectedProduct = new ProductViewTO();
        }
    }

    public String addButtonAction() {
        System.out.println("addButtonAction initiated...");
        selectedProduct = new ProductViewTO();
        currentScreenAction = ScreenAction.ADD;
        System.out.println("currentScreenAction = " + currentScreenAction);
        return "productDetail.xhtml";
    }

    public String editButtonAction() {
        System.out.println("editButtonAction initiated...");
        currentScreenAction = ScreenAction.EDIT;
        System.out.println("currentScreenAction = " + currentScreenAction);
        return "productDetail.xhtml";
    }

    public ProductViewTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductViewTO selectedProduct) {
        this.selectedProduct = selectedProduct;
        System.out.println("************************** setSelected() ran, the value of ScreenAction is: " + this.selectedProduct.getScreenAction());
    }

    public ScreenAction getCurrentScreenAction() {
        return currentScreenAction;
    }

    public void setCurrentScreenAction(ScreenAction currentScreenAction) {
        this.currentScreenAction = currentScreenAction;
    }

    public Map<String, String> getProductCategories() {
        Map<String, String> result = new HashMap<>();
        List<Category> allProductCategories = productCategoryService.findAll();
        for (Category productCategory : allProductCategories) {
            result.put(productCategory.getDescription(), productCategory.getCategoryId().toString());
        }
        return result;
    }

    public String saveButtonAction() {
        System.out.println("******************** Saving... currentScreenAction = " + currentScreenAction);
        System.out.println("In saveButtonAction, the value of selectedProduct = " + selectedProduct);
        if (currentScreenAction.equals(ScreenAction.ADD)) {
            System.out.println("***************** About to ADD *********************");
            Product product = new Product();
            product.setDescription(selectedProduct.getProductDescription());
            productService.save(product, selectedProduct.getProductCategoryId());
        } else if (currentScreenAction.equals(ScreenAction.EDIT)) {
            System.out.println("***************** About to EDIT *********************");
            Product selectedProductFromDB = productService.findById(selectedProduct.getProductId());
            selectedProductFromDB.setDescription(selectedProduct.getProductDescription());
            productService.save(selectedProductFromDB, selectedProduct.getProductCategoryId());
        }
        return "productList.xhtml?faces-redirect=true";
    }

    public String cancelButtonAction() {
        return "productList.xhtml?faces-redirect=true";
    }

    private void addMessage(String messageToDisplay) {
        FacesMessage messaage = new FacesMessage(FacesMessage.SEVERITY_INFO, messageToDisplay, null);
        FacesContext.getCurrentInstance().addMessage(null, messaage);
    }

}
