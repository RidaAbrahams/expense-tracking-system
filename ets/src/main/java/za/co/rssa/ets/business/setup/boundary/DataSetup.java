package za.co.rssa.ets.business.setup.boundary;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import za.co.rssa.ets.business.product.boundary.CategoryService;
import za.co.rssa.ets.business.product.boundary.ProductService;
import za.co.rssa.ets.business.product.boundary.ProductCategoryService;
import za.co.rssa.ets.business.product.entity.Category;
import za.co.rssa.ets.business.product.entity.Product;
import za.co.rssa.ets.business.product.entity.ProductCategory;

/**
 *
 * @author rida
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class DataSetup {

    @EJB
    private ProductService productService;
    @EJB
    private ProductCategoryService productCategoryService;
    @EJB
    private CategoryService categoryService;

    @PostConstruct
    public void init() {
        System.out.println("Creating sample data for products...");
        createCategoryAppliances();
        createSampleDataForProductCategoryApplicances();
        createCategoryBuildingMaterial();
        createSampleDataForProductCategoryBuildingMaterial();
        System.out.println("Sample data for products created successfully.");
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void createProductCategoryFruit() {
//        ProductCategory fruitProductCategory = new ProductCategory();
//        fruitProductCategory.setDescription("Fruit");
//        ProductCategory result = productCategoryService.save(fruitProductCategory);
//        System.out.println("Saved product category: " + result + " to DB...");
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void createSampleDataForProductCategoryFruit() {

//        ProductCategory productCategoryFruit = productCategoryService.findByDescription("Fruit");
//        System.out.println("Results of FindByDescription query is: " + productCategoryFruit);
//        // Many products belong to one product category
//        Product product1 = new Product();
//        product1.setDescription("Blueberry");
//        product1.setProductCategory(productCategoryFruit);
//        Product saved1 = productService.save(product1);
//        System.out.println("Saved product: " + saved1 + " to DB...");
//
//        Product product2 = new Product();
//        product2.setDescription("Raspberry");
//        product2.setProductCategory(productCategoryFruit);
//        Product saved2 = productService.save(product2);
//        System.out.println("Saved product: " + saved2 + " to DB...");
//
//        Product product3 = new Product();
//        product3.setDescription("Strawberry");
//        product3.setProductCategory(productCategoryFruit);
//        Product saved3 = productService.save(product3);
//        System.out.println("Saved product: " + saved3 + " to DB...");

    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void createProductCategoryVegatables() {
//        ProductCategory vegatableProductCategory = new ProductCategory();
//        vegatableProductCategory.setDescription("Vegatable");
//        ProductCategory result = productCategoryService.save(vegatableProductCategory);
//        System.out.println("Saved product category: " + result + " to DB...");
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void createSampleDataForProductCategoryVegatable() {

//        ProductCategory productCategoryVegatable = productCategoryService.findByDescription("Vegatable");
//        System.out.println("Results of findByDescription query is: " + productCategoryVegatable);
//        // Many products belong to one product category
//        Product product1 = new Product();
//        product1.setDescription("Zucchini");
//        product1.setProductCategory(productCategoryVegatable);
//        Product saved1 = productService.save(product1);
//        System.out.println("Saved product: " + saved1 + " to DB...");
//
//        Product product2 = new Product();
//        product2.setDescription("Spinach");
//        product2.setProductCategory(productCategoryVegatable);
//        Product saved2 = productService.save(product2);
//        System.out.println("Saved product: " + saved2 + " to DB...");
//
//        Product product3 = new Product();
//        product3.setDescription("Potato");
//        product3.setProductCategory(productCategoryVegatable);
//        Product saved3 = productService.save(product3);
//        System.out.println("Saved product: " + saved3 + " to DB...");

    }

    private void createCategoryAppliances() {
        Category productCategory = new Category();
        productCategory.setType("Product");
        productCategory.setDescription("Appliances");
        Category result = categoryService.save(productCategory);
        System.out.println("Saved product category: " + result + " to DB...");
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void createSampleDataForProductCategoryApplicances() {

        Category categoryAppliances = categoryService.findByDescription("Appliances");
        System.out.println("Results of FindByDescription query is: " + categoryAppliances);
        // Many products belong to one product category
        Product product1 = new Product();
        product1.setDescription("TV");
        product1.setProductCategory(categoryAppliances);
        Product saved1 = productService.save(product1);
        System.out.println("Saved product: " + saved1 + " to DB...");

        Product product2 = new Product();
        product2.setDescription("Fridge");
        product2.setProductCategory(categoryAppliances);
        Product saved2 = productService.save(product2);
        System.out.println("Saved product: " + saved2 + " to DB...");

        Product product3 = new Product();
        product3.setDescription("Stove");
        product3.setProductCategory(categoryAppliances);
        Product saved3 = productService.save(product3);
        System.out.println("Saved product: " + saved3 + " to DB...");

    }

    private void createCategoryBuildingMaterial() {
        Category productCategory = new Category();
        productCategory.setType("Product");
        productCategory.setDescription("Building material");
        Category result = categoryService.save(productCategory);
        System.out.println("Saved product category: " + result + " to DB...");
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void createSampleDataForProductCategoryBuildingMaterial() {

        Category categoryAppliances = categoryService.findByDescription("Building material");
        System.out.println("Results of FindByDescription query is: " + categoryAppliances);
        // Many products belong to one product category
        Product product1 = new Product();
        product1.setDescription("Cement");
        product1.setProductCategory(categoryAppliances);
        Product saved1 = productService.save(product1);
        System.out.println("Saved product: " + saved1 + " to DB...");

        Product product2 = new Product();
        product2.setDescription("Bricks");
        product2.setProductCategory(categoryAppliances);
        Product saved2 = productService.save(product2);
        System.out.println("Saved product: " + saved2 + " to DB...");

        Product product3 = new Product();
        product3.setDescription("Wood");
        product3.setProductCategory(categoryAppliances);
        Product saved3 = productService.save(product3);
        System.out.println("Saved product: " + saved3 + " to DB...");

    }
}
