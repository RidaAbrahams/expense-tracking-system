package za.co.rssa.ets.business.product.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import za.co.rssa.ets.business.product.entity.ProductCategory;

/**
 *
 * @author rida
 */
@Stateless
public class ProductCategoryService {

    @PersistenceContext
    EntityManager em;

    public List<ProductCategory> findAll() {
        return em.createNamedQuery(ProductCategory.findAll, ProductCategory.class).getResultList();
    }

    public ProductCategory findById(long id) {
        return em.find(ProductCategory.class, id);
    }

    public ProductCategory save(ProductCategory productCategory) {
        System.out.println("Saving ProductCategory: " + productCategory + " to the DB...");
        return em.merge(productCategory);
    }

    public ProductCategory findByDescription(String description) {
        TypedQuery<ProductCategory> query = em.createNamedQuery(ProductCategory.findByDesc, ProductCategory.class);
        query.setParameter("desc", description);
        query.setMaxResults(1);
        return query.getSingleResult();

    }

    public void delete(ProductCategory productCategory) {
        System.out.println("Deleting ProductCategory: " + productCategory + " to the DB...");
        em.remove(productCategory);
    }
}
