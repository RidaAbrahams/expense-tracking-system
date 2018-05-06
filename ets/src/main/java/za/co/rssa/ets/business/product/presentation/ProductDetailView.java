package za.co.rssa.ets.business.product.presentation;

import za.co.rssa.ets.business.common.presentation.ScreenAction;
import java.io.Serializable;
import java.util.ArrayList;
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
import za.co.rssa.ets.business.product.boundary.ProductService;
import za.co.rssa.ets.business.product.entity.Product;
import za.co.rssa.ets.business.category.entity.Category;
import za.co.rssa.ets.business.product.boundary.SizeService;
import za.co.rssa.ets.business.product.entity.Size;

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
    @EJB
    private SizeService sizeService;
    private ProductViewTO selectedProduct;
    private ScreenAction currentScreenAction;
    private List<SizeViewTO> selectedSizes;
    private List<SizeViewTO> sizes;

    @PostConstruct
    public void init() {
        if (selectedProduct == null) {
            selectedProduct = new ProductViewTO();
        }
        sizes = getAllSizes();
        System.out.println("Available sizes are:");
        for (SizeViewTO size : sizes) {
            System.out.println(size);
        }
        System.out.println("End of available sizes.");
//        if (selectedSizes == null) {
//            selectedSizes = new ArrayList<>();
//        }
    }

    public String addButtonAction() {
        System.out.println("addButtonAction initiated...");
        selectedProduct = new ProductViewTO();
        currentScreenAction = ScreenAction.ADD;
        selectedProduct.setScreenAction(currentScreenAction);
        System.out.println("currentScreenAction = " + currentScreenAction);
        return "productDetail.xhtml";
    }

    public String editButtonAction() {
        System.out.println("editButtonAction initiated...");
        currentScreenAction = ScreenAction.EDIT;
        selectedProduct.setScreenAction(currentScreenAction);
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
        List<Category> allProductCategories = productCategoryService.findByType(CategoryType.PRODUCT.getName());
        for (Category productCategory : allProductCategories) {
            result.put(productCategory.getDescription(), productCategory.getCategoryId().toString());
        }
        return result;
    }
    
    public List<SizeViewTO> getAllSizes() {
        List<SizeViewTO> result = new ArrayList<>();
        List<Size> allSizes = sizeService.findAll();
        for (Size size : allSizes) {
            SizeViewTO sizeViewTO = new SizeViewTO(size.getSizeId(), size.getDescription());
            result.add(sizeViewTO);
        }
        return result;
    }

    public String saveButtonAction() {
        System.out.println("******************** Saving... currentScreenAction = " + currentScreenAction);
        System.out.println("In saveButtonAction, the value of selectedProduct = " + selectedProduct);
        if (currentScreenAction.equals(ScreenAction.ADD)) {
            System.out.println("***************** About to ADD *********************");
            Product product = new Product();
            System.out.println("*** selectedProduct.getProductCategoryDescription() = [" + selectedProduct.getProductCategoryDescription() + "] ***");
            product.setDescription(selectedProduct.getProductDescription());
            List<Size> sizesToPersist = convertViewTOListToJPAEntityList(selectedSizes);
            product.setSizes(sizesToPersist);
            productService.save(product, selectedProduct.getProductCategoryId());
        } else if (currentScreenAction.equals(ScreenAction.EDIT)) {
            System.out.println("***************** About to EDIT *********************");
            Product selectedProductFromDB = productService.findById(selectedProduct.getProductId());
            selectedProductFromDB.setDescription(selectedProduct.getProductDescription());
            List<Size> sizesToPersist = convertViewTOListToJPAEntityList(selectedSizes);
            selectedProductFromDB.setSizes(sizesToPersist);
            productService.save(selectedProductFromDB, selectedProduct.getProductCategoryId());
        }
        return "productList.xhtml?faces-redirect=true";
    }
    
    private List<Size> convertViewTOListToJPAEntityList(List<SizeViewTO> sizeViewTOList) {
        List<Size> result = new ArrayList<>();
        for (SizeViewTO sizeId : sizeViewTOList) {
            Size convertedSize = convertSizeIdToJPAEntity(sizeId.getSizeId());
            result.add(convertedSize);
        }
        return result;
    }

    private Size convertSizeIdToJPAEntity(Long sizeId) {
        System.out.println("Value whilst attempting to convert is: " + sizeId);
        return sizeService.findById(sizeId);
    }

    public String cancelButtonAction() {
        return "productList.xhtml?faces-redirect=true";
    }

    public List<SizeViewTO> getSelectedSizes() {
        return selectedSizes;
    }

    public void setSelectedSizes(List<SizeViewTO> selectedSizes) {
        this.selectedSizes = selectedSizes;
    }

    public List<SizeViewTO> getSizes() {
        return sizes;
    }

    public void setSizes(List<SizeViewTO> sizes) {
        this.sizes = sizes;
    }
    
    private void addMessage(String messageToDisplay) {
        FacesMessage messaage = new FacesMessage(FacesMessage.SEVERITY_INFO, messageToDisplay, null);
        FacesContext.getCurrentInstance().addMessage(null, messaage);
    }

}
