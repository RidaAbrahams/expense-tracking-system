package za.co.rssa.ets.business.product.entity;

import za.co.rssa.ets.business.category.entity.Category;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rida
 */
@Entity
@Table(name = "Product")
@NamedQuery(name = Product.findAll, query = "SELECT p FROM Product p")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements Serializable {

    static final String PREFIX = "product.entity.Product.";
    public static final String findAll = PREFIX + "findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String description;

    @OneToOne
    @JoinColumn(name = "categoryId", updatable = false)
    private Category productCategory; // FK to Category table's categoryId column

    @OneToMany
    @JoinTable(name = "ProductSize",
            joinColumns = @JoinColumn(name = "productFK"),
            inverseJoinColumns = @JoinColumn(name = "sizeFK"))
    private List<Size> sizes;
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getProductCategory() {
        return productCategory;
    }
    
    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }
    
    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", description=" + description + ", productType=" + productCategory + '}';
    }

}
