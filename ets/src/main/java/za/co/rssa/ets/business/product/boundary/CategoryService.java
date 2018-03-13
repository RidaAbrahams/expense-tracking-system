package za.co.rssa.ets.business.product.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import za.co.rssa.ets.business.product.entity.Category;

/**
 *
 * @author rida
 */
@Stateless
public class CategoryService {

    @PersistenceContext
    EntityManager em;

    public List<Category> findAll() {
        return em.createNamedQuery(Category.findAll, Category.class).getResultList();
    }

    public Category findById(long id) {
        return em.find(Category.class, id);
    }

    public Category save(Category productCategory) {
        System.out.println("Saving Category: " + productCategory + " to the DB...");
        return em.merge(productCategory);
    }

    public Category findByDescription(String description) {
        TypedQuery<Category> query = em.createNamedQuery(Category.findByDesc, Category.class);
        query.setParameter("desc", description);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public List<Category> findByType(String type) {
        TypedQuery<Category> query = em.createNamedQuery(Category.findByType, Category.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    public void delete(Category category) {
        System.out.println("Deleting Category: " + category + " from the DB...");
        em.remove(category);
    }
}
