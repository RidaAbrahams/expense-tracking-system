package za.co.rssa.ets.business.product.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Size")
@NamedQuery(name = Size.findAll, query = "SELECT s FROM Size s")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Size implements Serializable {

    static final String PREFIX = "product.entity.Size.";
    public static final String findAll = PREFIX + "findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sizeId;
    private String description;

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Size{" + "sizeId=" + sizeId + ", description=" + description + '}';
    }
    
}
