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
@Table(name = "Category")
@NamedQueries({
    @NamedQuery(name = Category.findAll, query = "SELECT c FROM Category c"),
    @NamedQuery(name = Category.findByDesc, query = "SELECT c FROM Category c WHERE c.description = :desc"),
    @NamedQuery(name = Category.findByType, query = "SELECT c FROM Category c WHERE c.type = :type")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Category implements Serializable {

    static final String PREFIX = "product.entity.Category.";
    public static final String findAll = PREFIX + "findAll";
    public static final String findByDesc = PREFIX + "findByDesc";
    public static final String findByType = PREFIX + "findByType";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String description;
    private String type;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Category{" + "categoryId=" + categoryId + ", description=" + description + ", type=" + type + '}';
    }

}
