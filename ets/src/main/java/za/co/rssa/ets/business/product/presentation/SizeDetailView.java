package za.co.rssa.ets.business.product.presentation;

import za.co.rssa.ets.business.common.presentation.ScreenAction;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import za.co.rssa.ets.business.product.boundary.SizeService;
import za.co.rssa.ets.business.product.entity.Size;

/**
 *
 * @author rida
 */
@ManagedBean
@SessionScoped
public class SizeDetailView implements Serializable {

    @EJB
    private SizeService sizeService;
    private SizeViewTO selectedSize;
    private ScreenAction currentScreenAction;

    @PostConstruct
    public void init() {
        if (selectedSize == null) {
            selectedSize = new SizeViewTO();
        }
    }

    public String addButtonAction() {
        System.out.println("addButtonAction initiated...");
        selectedSize = new SizeViewTO();
        currentScreenAction = ScreenAction.ADD;
        System.out.println("currentScreenAction = " + currentScreenAction);
        return "sizeDetail.xhtml";
    }

    public String editButtonAction() {
        System.out.println("editButtonAction initiated...");
        currentScreenAction = ScreenAction.EDIT;
        System.out.println("currentScreenAction = " + currentScreenAction);
        return "sizeDetail.xhtml";
    }

    public SizeViewTO getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(SizeViewTO selectedSize) {
        this.selectedSize = selectedSize;
        System.out.println("************************** setSelected() ran, the value of ScreenAction is: " + this.selectedSize.getScreenAction());
    }

    public ScreenAction getCurrentScreenAction() {
        return currentScreenAction;
    }

    public void setCurrentScreenAction(ScreenAction currentScreenAction) {
        this.currentScreenAction = currentScreenAction;
    }

    public String saveButtonAction() {
        System.out.println("******************** Saving... currentScreenAction = " + currentScreenAction);
        System.out.println("In saveButtonAction, the value of selectedSize = " + selectedSize);
        if (currentScreenAction.equals(ScreenAction.ADD)) {
            System.out.println("***************** About to ADD *********************");
            Size size = new Size();
            size.setDescription(selectedSize.getSizeDescription());
            sizeService.save(size);
        } else if (currentScreenAction.equals(ScreenAction.EDIT)) {
            System.out.println("***************** About to EDIT *********************");
            Size selectedSizeFromDB = sizeService.findById(selectedSize.getSizeId());
            selectedSizeFromDB.setDescription(selectedSize.getSizeDescription());
            sizeService.save(selectedSizeFromDB);
        }
        return "sizeList.xhtml?faces-redirect=true";
    }

    public String cancelButtonAction() {
        return "sizeList.xhtml?faces-redirect=true";
    }

    private void addMessage(String messageToDisplay) {
        FacesMessage messaage = new FacesMessage(FacesMessage.SEVERITY_INFO, messageToDisplay, null);
        FacesContext.getCurrentInstance().addMessage(null, messaage);
    }

}
