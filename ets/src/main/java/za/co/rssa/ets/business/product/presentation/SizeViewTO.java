package za.co.rssa.ets.business.product.presentation;

import za.co.rssa.ets.business.common.presentation.ScreenAction;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author rida
 */
public class SizeViewTO implements Serializable {
    
    private Long sizeId;
    private String sizeDescription;
    private ScreenAction screenAction;

    public SizeViewTO() {
    }

    public SizeViewTO(Long sizeId, String sizeDescription) {
        this.sizeId = sizeId;
        this.sizeDescription = sizeDescription;
        this.screenAction = ScreenAction.NO_ACTION;
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

    public ScreenAction getScreenAction() {
        return screenAction;
    }

    public void setScreenAction(ScreenAction screenAction) {
        this.screenAction = screenAction;
    }

    /*
     * Very Important!!!!! If you are using a p:selectManyMenu component. You 
     * HAVE to override equals and hashcode or the converter class will not
     * work at all.
    */
    @Override
    public int hashCode() {
        return Objects.hash(sizeId, sizeDescription);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SizeViewTO)) {
            return false;
        }
        SizeViewTO sizeViewTO = (SizeViewTO) obj;
        return sizeViewTO.sizeId == sizeId && sizeViewTO.sizeDescription == sizeDescription;
    }

    @Override
    public String toString() {
        return "SizeViewTO{" + "sizeId=" + sizeId + ", sizeDescription=" + sizeDescription + ", screenAction=" + screenAction + '}';
    }
    
}
