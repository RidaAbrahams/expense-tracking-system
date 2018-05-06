package za.co.rssa.ets.business.product.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import za.co.rssa.ets.business.product.entity.Size;

/**
 *
 * @author rida
 */
@Stateless
public class SizeService {

    @PersistenceContext
    EntityManager em;

    public List<Size> findAll() {
        return em.createNamedQuery(Size.findAll, Size.class).getResultList();
    }

    public Size findById(long id) {
        return em.find(Size.class, id);
    }

    public Size save(Size size) {
        System.out.println("Saving Size: " + size + " to the DB...");
        return em.merge(size);
    }

    public void delete(Size size) {
        System.out.println("Deleting Size: " + size + " from the DB...");
        em.remove(em.merge(size));
    }

    public List<Size> findSizesBy(String sizeDescription) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT s FROM Size s WHERE 1=1");

        if (sizeDescription != null && !sizeDescription.trim().isEmpty()) {
            query.append(" AND UPPER(s.description) LIKE :desc");
        }
        Query q = em.createQuery(query.toString());
        if (sizeDescription != null && !sizeDescription.trim().isEmpty()) {
            q.setParameter("desc", sizeDescription.trim().toUpperCase() + "%");
        }
        return (List<Size>) q.getResultList();
    }
}