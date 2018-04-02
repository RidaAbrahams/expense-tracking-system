package za.co.rssa.ets.business.product.presentation;

import za.co.rssa.ets.business.common.presentation.ScreenAction;
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
import za.co.rssa.ets.business.product.boundary.ProductService;
import za.co.rssa.ets.business.product.entity.Product;
import za.co.rssa.ets.business.category.entity.Category;

/**
 *
 * @author rida
 */
@ManagedBean
@SessionScoped
public class ProductListView implements Serializable {

    @EJB
    private ProductService productService;
    @EJB
    private CategoryService productCategoryService;
    private ProductViewTO currentRowInTable;
    private String searchProductDescription;
    private String searchProductCategory;

    public String searchButtonAction() {
        getProducts();
        return "productList.xhtml?faces-redirect=true";
    }

    public String addButtonAction() {
        System.out.println("addButtonAction initiated...");
        return "productDetail.xhtml";
    }

    public String deleteButtonAction() {
        System.out.println("deleteButtonAction initiated...");
        Product currentProduct = productService.findById(currentRowInTable.getProductId());
        productService.delete(currentProduct);
        return "productList.xhtml?faces-redirect=true";
    }

    public String backButtonAction() {
        return "/index.xhtml?faces-redirect=true";
    }
    
    public Map<String, String> getProductCategories() {
        Map<String, String> result = new HashMap<>();
        List<Category> allProductCategories = productCategoryService.findByType(CategoryType.PRODUCT.getName());
        for (Category productCategory : allProductCategories) {
            result.put(productCategory.getDescription(), productCategory.getCategoryId().toString());
        }
        return result;
    }

    public List<ProductViewTO> getProducts() {
        List<ProductViewTO> result = new ArrayList<>();
        List<Product> allProducts = productService.findProductsBy(searchProductDescription, searchProductCategory);
        populateViewWithDBResults(allProducts, result);
        return result;
    }

    private void populateViewWithDBResults(List<Product> allProducts, List<ProductViewTO> result) {
        for (Product product : allProducts) {
            ProductViewTO productViewTO = new ProductViewTO();
            productViewTO.setProductId(product.getProductId());
            productViewTO.setProductDescription(product.getDescription());
            productViewTO.setProductCategoryId(product.getProductCategory().getCategoryId());
            productViewTO.setProductCategoryDescription(product.getProductCategory().getDescription());
            productViewTO.setScreenAction(ScreenAction.EDIT);
            result.add(productViewTO);
        }
    }

    public ProductViewTO getCurrentRowInTable() {
        return currentRowInTable;
    }

    public void setCurrentRowInTable(ProductViewTO currentRowInTable) {
        this.currentRowInTable = currentRowInTable;
    }

    public String getSearchProductDescription() {
        return searchProductDescription;
    }

    public void setSearchProductDescription(String searchProductDescription) {
        this.searchProductDescription = searchProductDescription;
    }

    public String getSearchProductCategory() {
        return searchProductCategory;
    }

    public void setSearchProductCategory(String searchProductCategory) {
        this.searchProductCategory = searchProductCategory;
    }

}
