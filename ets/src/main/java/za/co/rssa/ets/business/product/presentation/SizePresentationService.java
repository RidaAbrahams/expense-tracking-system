package za.co.rssa.ets.business.product.presentation;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import za.co.rssa.ets.business.product.boundary.SizeService;
import za.co.rssa.ets.business.product.entity.Size;

/**
 *
 * @author rida
 */
@ManagedBean(name = "sizePresentationService", eager = true)
@ApplicationScoped
public class SizePresentationService {
    
    @EJB
    SizeService sizeService;
    private List<SizeViewTO> sizeViewTOList;
    
    @PostConstruct
    public void init() {
        sizeViewTOList = convertToSizeViewTOList(sizeService.findAll());
    }
    
    private List<SizeViewTO> convertToSizeViewTOList(List<Size> listOfSizeEntities) {
        List<SizeViewTO> result = new ArrayList<>();
        for (Size sizeEntity : listOfSizeEntities) {
            result.add(new SizeViewTO(sizeEntity.getSizeId(), sizeEntity.getDescription()));
        }
        return result;
    }

    public List<SizeViewTO> getSizeViewTOList() {
        return sizeViewTOList;
    }
    
    public SizeViewTO getSizeViewTOFor(Long sizeId) {
        Size resultingSize = sizeService.findById(sizeId);
        return new SizeViewTO(resultingSize.getSizeId(), resultingSize.getDescription());
    }
    
    public SizeViewTO getSizeViewTOFor(String sizeDescription) {
        List<Size> sizeList = sizeService.findSizesBy(sizeDescription);
        Size resultingSize = null;
        if (sizeList != null && !sizeList.isEmpty()) {
            resultingSize = sizeList.get(0);
        }
        return new SizeViewTO(resultingSize.getSizeId(), resultingSize.getDescription());
    }
    
}
