package za.co.rssa.ets.business.product.presentation;

import za.co.rssa.ets.business.common.presentation.ScreenAction;
import java.io.Serializable;

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

    @Override
    public String toString() {
        return "SizeViewTO{" + "sizeId=" + sizeId + ", sizeDescription=" + sizeDescription + ", screenAction=" + screenAction + '}';
    }
    
}
