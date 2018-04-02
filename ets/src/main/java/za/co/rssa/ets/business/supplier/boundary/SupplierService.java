package za.co.rssa.ets.business.supplier.boundary;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import za.co.rssa.ets.business.category.boundary.CategoryService;
import za.co.rssa.ets.business.category.entity.Category;
import za.co.rssa.ets.business.product.entity.Product;
import za.co.rssa.ets.business.supplier.entity.Supplier;

/**
 *
 * @author rida
 */
@Stateless
public class SupplierService {

    @EJB
    private CategoryService supplierCategoryService;

    @PersistenceContext
    EntityManager em;

    public List<Supplier> findAll() {
        return em.createNamedQuery(Supplier.findAll, Supplier.class).getResultList();
    }

    public Supplier findById(long id) {
        return em.find(Supplier.class, id);
    }

    public Supplier save(Supplier supplier, Long supplierCategoryId) {
        Category supplierCategory = supplierCategoryService.findById(supplierCategoryId);
        supplier.setSupplierCategory(supplierCategory);
        System.out.println("Saving Supplier: " + supplier + " to the DB whilst fetching the SupplierCategory dynamically...");
        return em.merge(supplier);
    }

    public Supplier save(Supplier supplier) {
        System.out.println("Saving Supplier: " + supplier + " to the DB...");
        return em.merge(supplier);
    }

    public void delete(Supplier supplier) {
        System.out.println("Deleting Supplier: " + supplier + " to the DB...");
        em.remove(em.merge(supplier));
    }

    public List<Supplier> findSuppliersBy(String supplierName, String supplierCategoryId) {
        System.out.println("supplierCategory in query = " + supplierCategoryId);
        StringBuilder query = new StringBuilder();
        query.append("SELECT p FROM Supplier p WHERE 1=1");

        if (supplierName != null && !supplierName.trim().isEmpty()) {
            query.append(" AND UPPER(p.name) LIKE :name");
        }
        if (supplierCategoryId != null && !supplierCategoryId.trim().isEmpty()) {
            query.append(" AND p.supplierCategory.categoryId = :categoryId");
        }
        Query q = em.createQuery(query.toString());
        if (supplierName != null && !supplierName.trim().isEmpty()) {
            q.setParameter("name", supplierName.trim().toUpperCase() + "%");
        }
        if (supplierCategoryId != null && !supplierCategoryId.isEmpty()) {
            q.setParameter("categoryId", Long.valueOf(supplierCategoryId));
        }
        return (List<Supplier>) q.getResultList();
    }
}
