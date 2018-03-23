package za.co.rssa.ets.business.supplier.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import za.co.rssa.ets.business.category.entity.Category;

/**
 *
 * @author rida
 */
@Entity
@Table(name = "Supplier")
@NamedQuery(name = Supplier.findAll, query = "SELECT s FROM Supplier s")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Supplier implements Serializable {

    static final String PREFIX = "supplier.entity.Supplier.";
    public static final String findAll = PREFIX + "findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;
    private String name;
    private String category;
    @OneToOne
    @JoinColumn(name = "categoryId", updatable = false)
    private Category supplierCategory;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Category getSupplierCategory() {
        return supplierCategory;
    }

    public void setSupplierCategory(Category supplierCategory) {
        this.supplierCategory = supplierCategory;
    }

    @Override
    public String toString() {
        return "Supplier{" + "name=" + name + ", category=" + category + ", supplierCategory=" + supplierCategory + '}';
    }

}
