package za.co.rssa.ets.business.category.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import za.co.rssa.ets.business.category.entity.Category;
import za.co.rssa.ets.business.product.entity.Product;

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

    public Category save(Category category) {
        System.out.println("Saving Category: " + category + " to the DB...");
        return em.merge(category);
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

    public List<Category> findCategoriesBy(String categoryDescription, String categoryType) {
        System.out.println("category in query = " + categoryType);
        StringBuilder query = new StringBuilder();
        query.append("SELECT c FROM Category c WHERE 1=1");

        if (categoryDescription != null && !categoryDescription.trim().isEmpty()) {
            query.append(" AND UPPER(c.description) LIKE :desc");
        }
        if (categoryType != null && !categoryType.trim().isEmpty()) {
            query.append(" AND c.type = :type");
        }
        Query q = em.createQuery(query.toString());
        if (categoryDescription != null && !categoryDescription.trim().isEmpty()) {
            q.setParameter("desc", categoryDescription.trim().toUpperCase() + "%");
        }
        if (categoryType != null && !categoryType.isEmpty()) {
            q.setParameter("type", categoryType);
        }
        return (List<Category>) q.getResultList();
    }
}
