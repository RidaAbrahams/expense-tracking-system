package za.co.rssa.ets.business.product.presentation;

import java.io.Serializable;

/**
 *
 * @author rida
 */
public class ProductSizeViewTO implements Serializable {
    
    private Long sizeId;
    private String sizeDescription;

    public ProductSizeViewTO() {
    }

    public ProductSizeViewTO(Long sizeId, String sizeDescription) {
        this.sizeId = sizeId;
        this.sizeDescription = sizeDescription;
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeDescription() {
        return sizeDescription;
    }

    public void setSizeDescription(String sizeDescription) {
        this.sizeDescription = sizeDescription;
    }
    
}
