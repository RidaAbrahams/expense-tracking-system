package za.co.rssa.ets.business.product.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import za.co.rssa.ets.business.product.entity.Size;

/**
 *
 * @author rida
 */
@FacesConverter("sizeConverter")
public class SizeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("***************** 1. Running getAsObject. The value of the value var is " + value);
        if (value != null && value.trim().length() > 0) {
            try {
                SizePresentationService service = (SizePresentationService) context.getExternalContext().getApplicationMap().get("sizePresentationService");
                SizeViewTO result = service.getSizeViewTOFor(Long.parseLong(value));
                System.out.println("The object for value: " + value + " is :" + result);
                return result;
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Size."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("***************** 1. Running getAsString.");
        if (value != null) {
            SizeViewTO to = (SizeViewTO) value;
            System.out.println("***************** 3. Running getAsString. The value of the Object is: " + to);
            SizeViewTO viewTO = (SizeViewTO) value;
            Long sizeId = viewTO.getSizeId();
            String sizeIdString = String.valueOf(sizeId);
            return sizeIdString;
        } else {
            return null;
        }
    }

    private SizeViewTO convertSizeEntityToSizeViewTO(Size givenSize) {
        return new SizeViewTO(givenSize.getSizeId(), givenSize.getDescription());
    }

}
