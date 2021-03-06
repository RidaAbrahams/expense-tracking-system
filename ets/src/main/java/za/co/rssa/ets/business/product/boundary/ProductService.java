package za.co.rssa.ets.business.product.boundary;

import za.co.rssa.ets.business.category.boundary.CategoryService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import za.co.rssa.ets.business.category.entity.Category;
import za.co.rssa.ets.business.product.entity.Product;

/**
 *
 * @author rida
 */
@Stateless
public class ProductService {

    @EJB
    private CategoryService productCategoryService;
            
    @PersistenceContext
    EntityManager em;

    public List<Product> findAll() {
        return em.createNamedQuery(Product.findAll, Product.class).getResultList();
    }

    public Product findById(long id) {
        return em.find(Product.class, id);
    }

    public Product save(Product product, Long productCategoryId) {
        Category productCategory = productCategoryService.findById(productCategoryId);
        product.setProductCategory(productCategory);
        System.out.println("Saving Product: " + product + " to the DB whilst fetching the ProductCategory dynamically...");
        return em.merge(product);
    }
    
    public Product save(Product product) {
        System.out.println("Saving Product: " + product + " to the DB...");
        return em.merge(product);
    }

    public void delete(Product product) {
        System.out.println("Deleting Product: " + product + " to the DB...");
        em.remove(em.merge(product));
    }
    
    public List<Product> findProductsBy(String productDescription, String productCategoryId) {
        System.out.println("productCategory in query = " + productCategoryId);
        StringBuilder query = new StringBuilder();
        query.append("SELECT p FROM Product p WHERE 1=1");
        
        if (productDescription != null && !productDescription.trim().isEmpty()) {
            query.append(" AND UPPER(p.description) LIKE :desc");
        }
        if (productCategoryId != null && !productCategoryId.trim().isEmpty()) {
            query.append(" AND p.productCategory.categoryId = :categoryId");
        }
        Query q = em.createQuery(query.toString());
        if (productDescription != null && !productDescription.trim().isEmpty()) {
            q.setParameter("desc", productDescription.trim().toUpperCase() + "%");
        }
        if (productCategoryId != null && !productCategoryId.isEmpty()) {
            q.setParameter("categoryId", Long.valueOf(productCategoryId));
        }
        return (List<Product>)q.getResultList();
    }
    
}
