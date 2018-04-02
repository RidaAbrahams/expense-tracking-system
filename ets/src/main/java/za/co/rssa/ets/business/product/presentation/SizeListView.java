package za.co.rssa.ets.business.product.presentation;

import za.co.rssa.ets.business.common.presentation.ScreenAction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import za.co.rssa.ets.business.product.boundary.SizeService;
import za.co.rssa.ets.business.product.entity.Size;

/**
 *
 * @author rida
 */
@ManagedBean
@SessionScoped
public class SizeListView implements Serializable {

    @EJB
    private SizeService sizeService;
    private SizeViewTO currentRowInTable;
    private String searchSizeDescription;

    public String searchButtonAction() {
        getSizes();
        return "sizeList.xhtml?faces-redirect=true";
    }

    public String addButtonAction() {
        System.out.println("addButtonAction initiated...");
        return "sizeDetail.xhtml";
    }

    public String deleteButtonAction() {
        System.out.println("deleteButtonAction initiated...");
        Size currentSize = sizeService.findById(currentRowInTable.getSizeId());
        sizeService.delete(currentSize);
        return "sizeList.xhtml?faces-redirect=true";
    }

    public String backButtonAction() {
        return "/index.xhtml?faces-redirect=true";
    }
    
    public List<SizeViewTO> getSizes() {
        List<SizeViewTO> result = new ArrayList<>();
        List<Size> allSizes = sizeService.findSizesBy(searchSizeDescription);
        populateViewWithDBResults(allSizes, result);
        return result;
    }

    private void populateViewWithDBResults(List<Size> allSizes, List<SizeViewTO> result) {
        for (Size size : allSizes) {
            SizeViewTO sizeViewTO = new SizeViewTO();
            sizeViewTO.setSizeId(size.getSizeId());
            sizeViewTO.setSizeDescription(size.getDescription());
            sizeViewTO.setScreenAction(ScreenAction.EDIT);
            result.add(sizeViewTO);
        }
    }

    public SizeViewTO getCurrentRowInTable() {
        return currentRowInTable;
    }

    public void setCurrentRowInTable(SizeViewTO currentRowInTable) {
        this.currentRowInTable = currentRowInTable;
    }

    public String getSearchSizeDescription() {
        return searchSizeDescription;
    }

    public void setSearchSizeDescription(String searchSizeDescription) {
        this.searchSizeDescription = searchSizeDescription;
    }

}
