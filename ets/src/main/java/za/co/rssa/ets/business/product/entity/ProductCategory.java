package za.co.rssa.ets.business.product.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rida
 */
@Entity
@Table(name = "productCategory")
@NamedQueries({
    @NamedQuery(name = ProductCategory.findAll, query = "SELECT pc FROM ProductCategory pc"),
    @NamedQuery(name = ProductCategory.findByDesc, query = "SELECT pc FROM ProductCategory pc WHERE pc.description = :desc")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductCategory implements Serializable {

    static final String PREFIX = "product.entity.ProductCategory.";
    public static final String findAll = PREFIX + "findAll";
    public static final String findByDesc = PREFIX + "findByDesc";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCategoryId;
    private String description;
//    @OneToMany(targetEntity = Product.class, mappedBy = "ProductCategory")
//    private Set<Product> product;

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Set<Product> getProduct() {
//        return product;
//    }
//
//    public void setProduct(Set<Product> product) {
//        this.product = product;
//    }
    
    @Override
    public String toString() {
        return "ProductCategory{" + "productCategoryId=" + productCategoryId + ", description=" + description + '}';
    }
    
}
